package com.toreva.mobile.data.api.dto

data class ReceiptResponse(
    val id: String,
    val strategyId: String,
    val type: String,
    val action: String,
    val status: String,
    val executedAt: String,
    val signatures: List<String>,
    val fromVenue: String?,
    val toVenue: String?,
    val fromApyBps: Int?,
    val toApyBps: Int?,
    val yieldDiffBps: Int?,
)
