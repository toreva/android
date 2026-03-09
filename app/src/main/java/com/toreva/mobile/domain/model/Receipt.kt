package com.toreva.mobile.domain.model

data class Receipt(
    val id: String,
    val type: String,
    val action: String,
    val status: String,
    val signatures: List<String>,
    val venue: String?,
    val apy: String?,
)
