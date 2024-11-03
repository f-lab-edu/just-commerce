package com.justcommerce.item.domain

data class Cart (
    val id: Int,
    val items: List<Item>
)
