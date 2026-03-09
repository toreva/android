package com.toreva.mobile.data.repository

import com.toreva.mobile.data.api.TorevaApi
import com.toreva.mobile.data.api.dto.CreateIntentRequest
import com.toreva.mobile.data.api.dto.IntentPolicy
import java.util.UUID

class IntentRepository(private val api: TorevaApi) {
    suspend fun activate(vaultId: String): String {
        val response = api.createIntent(
            CreateIntentRequest(
                idempotencyKey = UUID.randomUUID().toString(),
                vaultId = vaultId,
                strategyId = "usdc",
                action = "activate",
                policy = IntentPolicy(
                    modules = listOf("usdc"),
                    riskMode = "safe",
                    allocations = mapOf("usdc" to 100, "sol" to 0, "memes" to 0),
                ),
            ),
        )
        return response.intentId
    }

    suspend fun getIntent(intentId: String) = api.getIntent(intentId)
}
