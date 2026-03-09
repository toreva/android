package com.toreva.mobile.data.repository

import com.toreva.mobile.data.api.TorevaApi

class ReceiptRepository(private val api: TorevaApi) {
    suspend fun getReceipt(receiptId: String) = api.getReceipt(receiptId)
}
