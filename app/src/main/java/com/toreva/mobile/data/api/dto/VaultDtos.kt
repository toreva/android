package com.toreva.mobile.data.api.dto

data class BuildVaultTxRequest(val walletAddress: String, val cluster: String)
data class BuildVaultTxResponse(
    val serializedTransaction: String,
    val vaultPda: String,
    val recentBlockhash: String,
    val expiresAt: String,
)

data class ConfirmVaultRequest(val vaultPda: String, val txSignature: String, val cluster: String)
data class ConfirmVaultResponse(val vaultId: String, val status: String, val confirmedAt: String)
