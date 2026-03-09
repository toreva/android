package com.toreva.mobile.data.wallet

import android.content.Context
import android.net.Uri
import androidx.activity.ComponentActivity
import com.solana.mobilewalletadapter.clientlib.ActivityResultSender
import com.solana.mobilewalletadapter.clientlib.AdapterOperations
import com.solana.mobilewalletadapter.clientlib.AssociationScenarioProvider
import com.solana.mobilewalletadapter.clientlib.ConnectionIdentity
import com.solana.mobilewalletadapter.clientlib.MobileWalletAdapter
import com.solana.mobilewalletadapter.clientlib.RpcCluster
import com.solana.mobilewalletadapter.clientlib.TransactionResult
import com.solana.mobilewalletadapter.clientlib.protocol.MobileWalletAdapterClient
import com.toreva.mobile.BuildConfig
import kotlinx.coroutines.Dispatchers
import org.sol4k.Base58
import java.lang.ref.WeakReference

class MwaClient(private val context: Context) {
    companion object {
        private const val IDENTITY_NAME = "Toreva"
        private val IDENTITY_URI = Uri.parse("https://app.toreva.com")
        private val ICON_URI = Uri.parse("https://app.toreva.com/favicon.ico")
        private var currentActivityRef: WeakReference<ComponentActivity>? = null

        fun setCurrentActivity(activity: ComponentActivity?) {
            currentActivityRef = if (activity == null) null else WeakReference(activity)
        }
    }

    private fun requireActivity(): ComponentActivity {
        return requireNotNull(currentActivityRef?.get()) {
            "No active ComponentActivity for Mobile Wallet Adapter flow"
        }
    }

    private fun resolveRpcCluster(): RpcCluster {
        return when (BuildConfig.SOLANA_CLUSTER.lowercase()) {
            "mainnet", "mainnet-beta" -> RpcCluster.MainnetBeta
            "testnet" -> RpcCluster.Testnet
            else -> RpcCluster.Devnet
        }
    }

    private fun newAdapter(authToken: String? = null): MobileWalletAdapter {
        return MobileWalletAdapter(
            connectionIdentity = ConnectionIdentity(IDENTITY_URI, ICON_URI, IDENTITY_NAME),
            timeout = 60_000,
            ioDispatcher = Dispatchers.IO,
            scenarioProvider = AssociationScenarioProvider(),
        ).apply {
            if (!authToken.isNullOrBlank()) {
                this.authToken = authToken
            }
            this.rpcCluster = resolveRpcCluster()
        }
    }

    suspend fun authorize(): MwaAuthResult {
        val activity = requireActivity()
        val adapter = newAdapter()
        val result = adapter.transact(
            sender = ActivityResultSender(activity),
            signInPayload = null,
        ) { authResult: MobileWalletAdapterClient.AuthorizationResult ->
            authResult
        }
        val authResult: MobileWalletAdapterClient.AuthorizationResult = extractPayload(result)
        val walletPublicKey = authResult.accounts?.firstOrNull()?.publicKey ?: authResult.publicKey
        val walletAddress = requireNotNull(walletPublicKey) {
            "Wallet authorization returned no account public key"
        }
        return MwaAuthResult(
            walletAddress = Base58.encode(walletAddress),
            authToken = requireNotNull(authResult.authToken) {
                "Wallet authorization returned no auth token"
            },
        )
    }

    suspend fun signMessage(message: ByteArray, walletAddress: ByteArray, authToken: String): ByteArray {
        val activity = requireActivity()
        val adapter = newAdapter(authToken)
        val result = adapter.transact(
            sender = ActivityResultSender(activity),
            signInPayload = null,
        ) { _: MobileWalletAdapterClient.AuthorizationResult ->
            val signResult = signMessages(arrayOf(message), arrayOf(walletAddress))
            requireNotNull(signResult.signedPayloads.firstOrNull()) {
                "Wallet did not return signed message payload"
            }
        }
        return extractPayload(result)
    }

    suspend fun signTransaction(serializedTx: ByteArray, authToken: String): ByteArray {
        val activity = requireActivity()
        val adapter = newAdapter(authToken)
        val result = adapter.transact(
            sender = ActivityResultSender(activity),
            signInPayload = null,
        ) { _: MobileWalletAdapterClient.AuthorizationResult ->
            val signResult = signTransactions(arrayOf(serializedTx))
            requireNotNull(signResult.signedPayloads.firstOrNull()) {
                "Wallet did not return signed transaction payload"
            }
        }
        return extractPayload(result)
    }

    private fun <T> extractPayload(result: TransactionResult<T>): T {
        return when (result) {
            is TransactionResult.Success -> result.payload
            is TransactionResult.NoWalletFound ->
                error("No compatible mobile wallet found: ${result.message}")
            is TransactionResult.Failure ->
                throw IllegalStateException("Mobile wallet operation failed: ${result.message}", result.e)
            else -> error("Unknown wallet transaction result type: ${result::class.java.name}")
        }
    }
}

data class MwaAuthResult(val walletAddress: String, val authToken: String)
