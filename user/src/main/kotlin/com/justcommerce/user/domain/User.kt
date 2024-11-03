package com.justcommerce.user.domain

import java.time.LocalDateTime

class User (
    val id: Long,
    val name: String,
    val address: String,
    val createdAt: LocalDateTime
)
