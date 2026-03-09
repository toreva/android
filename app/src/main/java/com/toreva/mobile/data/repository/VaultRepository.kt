package com.toreva.mobile.data.repository

import android.util.Base64
import com.toreva.mobile.BuildConfig
import com.toreva.mobile.data.api.TorevaApi
import com.toreva.mobile.data.api.dto.BuildVaultTxRequest
import com.toreva.mobile.data.api.dto.ConfirmVaultRequest
import com.toreva.mobile.data.session.SessionStore
import com.toreva.mobile.data.wallet.MwaClient

class VaultRepository(
    private val api: TorevaApi,
    private val sessionStore: SessionStore,
    private val mwaClient: MwaClient,
) {
    suspend fun createVault(): String {
        val wallet = requireNotNull(sessionStore.getWalletAddress())
        val mwaToken = requireNotNull(sessionStore.getMwaToken()) { "Missing MWA auth token" }
        val build = api.buildVaultTx(BuildVaultTxRequest(wallet, BuildConfig.SOLANA_CLUSTER))
        val signed = mwaClient.signTransaction(
            Base64.decode(build.serializedTransaction, Base64.DEFAULT),
            mwaToken,
        )
        val txSignature = Base64.encodeToString(signed, Base64.NO_WRAP)
        return api.confirmVault(ConfirmVaultRequest(build.vaultPda, txSignature, BuildConfig.SOLANA_CLUSTER)).vaultId
    }
}
