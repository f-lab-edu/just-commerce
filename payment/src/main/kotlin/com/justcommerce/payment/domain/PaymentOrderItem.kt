package com.justcommerce.payment.domain

data class PaymentOrderItem (
    val itemId: String,
    val orderId: String,
    val sellerId: Long,
    val price: Long
)
