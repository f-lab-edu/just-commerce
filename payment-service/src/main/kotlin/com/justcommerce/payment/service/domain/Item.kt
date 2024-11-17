package com.justcommerce.payment.service.domain

data class Item (
    val id: String,
    val title: String,
    val price: Long,
    val sellerId: Long
)
