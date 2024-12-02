package com.justcommerce.payment.controller.port

data class ConfirmationCommand (
    val paymentKey: String,
    val orderId: String,
    val amount: Long
)
