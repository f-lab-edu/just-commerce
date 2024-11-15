package com.justcommerce.item.domain

import java.time.LocalDateTime

class Item (
    val id: String,
    val title: String,
    val categories: List<Category>,
    val price: Long,
    val sellerId: Long,
    val createdAt: LocalDateTime
)
