package com.justcommerce.payment.service.domain

data class PaymentOrderItem (
    val itemId: String,
    val orderId: String,
    val sellerId: Long,
    val price: Long
)
