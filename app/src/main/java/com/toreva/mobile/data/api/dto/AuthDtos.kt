package com.toreva.mobile.data.api.dto

data class ChallengeRequest(val walletAddress: String)
data class ChallengeResponse(val challenge: String, val nonce: String, val expiresAt: String)

data class VerifyRequest(val walletAddress: String, val nonce: String, val signature: String)
data class VerifyResponse(
    val sessionToken: String,
    val accountId: String,
    val vaultId: String?,
    val vaultStatus: String,
    val expiresAt: String,
)
