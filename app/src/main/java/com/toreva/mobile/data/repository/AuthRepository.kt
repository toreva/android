package com.toreva.mobile.data.repository

import android.util.Base64
import com.toreva.mobile.data.api.TorevaApi
import com.toreva.mobile.data.api.dto.ChallengeRequest
import com.toreva.mobile.data.api.dto.VerifyRequest
import com.toreva.mobile.data.session.SessionStore
import com.toreva.mobile.data.wallet.MwaClient
import org.sol4k.Base58

class AuthRepository(
    private val api: TorevaApi,
    private val mwaClient: MwaClient,
    private val sessionStore: SessionStore,
) {
    suspend fun connectAndAuthenticate(): String {
        val auth = mwaClient.authorize()
        val challenge = api.getChallenge(ChallengeRequest(auth.walletAddress))
        val signature = mwaClient.signMessage(
            challenge.challenge.toByteArray(),
            Base58.decode(auth.walletAddress),
            auth.authToken,
        )
        val verify = api.verifySignature(
            VerifyRequest(
                walletAddress = auth.walletAddress,
                nonce = challenge.nonce,
                signature = Base64.encodeToString(signature, Base64.NO_WRAP),
            ),
        )
        sessionStore.saveSession(verify.sessionToken, auth.walletAddress, verify.accountId, verify.vaultId, verify.vaultStatus, auth.authToken)
        return verify.vaultStatus
    }
}
