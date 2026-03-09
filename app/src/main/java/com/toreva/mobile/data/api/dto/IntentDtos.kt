package com.toreva.mobile.data.api.dto

data class CreateIntentRequest(
    val idempotencyKey: String,
    val vaultId: String,
    val strategyId: String,
    val action: String,
    val policy: IntentPolicy,
)

data class IntentPolicy(val modules: List<String>, val riskMode: String, val allocations: Map<String, Int>)

data class CreateIntentResponse(val intentId: String, val status: String, val message: String, val createdAt: String)

data class IntentStatusResponse(
    val intentId: String,
    val status: String,
    val strategyId: String?,
    val lastExecution: LastExecution?,
    val executionCount: Int,
)

data class LastExecution(val receiptId: String, val action: String, val status: String, val executedAt: String)
