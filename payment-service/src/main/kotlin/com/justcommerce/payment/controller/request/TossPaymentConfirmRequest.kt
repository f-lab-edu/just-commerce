package com.justcommerce.payment.controller.request

data class TossPaymentConfirmRequest (
    val paymentKey: String,
    val orderId: String,
    val amount: Long
)
