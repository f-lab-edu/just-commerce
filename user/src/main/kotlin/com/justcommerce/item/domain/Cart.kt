package com.justcommerce.item.domain

data class Cart (
    val cartId: Long,
    val userId: Long,
    val items: List<Item>
)
