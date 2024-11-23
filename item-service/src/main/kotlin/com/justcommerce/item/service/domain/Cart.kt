package com.justcommerce.item.service.domain

data class Cart (
    val cartId: Long,
    val userId: Long,
    val items: List<Item>
)
