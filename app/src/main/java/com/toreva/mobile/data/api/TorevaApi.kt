package com.toreva.mobile.data.api

import com.toreva.mobile.data.api.dto.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TorevaApi {
    @POST("auth/challenge")
    suspend fun getChallenge(@Body req: ChallengeRequest): ChallengeResponse

    @POST("auth/verify")
    suspend fun verifySignature(@Body req: VerifyRequest): VerifyResponse

    @POST("vault/build-create-tx")
    suspend fun buildVaultTx(@Body req: BuildVaultTxRequest): BuildVaultTxResponse

    @POST("vault/confirm")
    suspend fun confirmVault(@Body req: ConfirmVaultRequest): ConfirmVaultResponse

    @POST("intents")
    suspend fun createIntent(@Body req: CreateIntentRequest): CreateIntentResponse

    @GET("intents/{intentId}")
    suspend fun getIntent(@Path("intentId") id: String): IntentStatusResponse

    @GET("receipts/{receiptId}")
    suspend fun getReceipt(@Path("receiptId") id: String): ReceiptResponse

    @GET("strategies")
    suspend fun getStrategies(): StrategiesResponse
}
