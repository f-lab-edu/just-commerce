package com.justcommerce.payment.domain

data class Item (
    val id: String,
    val title: String,
    val price: String,
    val sellerId: Long
)
