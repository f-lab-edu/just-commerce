package com.justcommerce.payment.controller.port

data class CheckoutResult (
    val orderId: String,
    val amount: Long,
    val customerName: String,
    val customerEmail: String,
    val customerMobilePhone: String,
)
