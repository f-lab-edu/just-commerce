package com.justcommerce.user.service.domain

import java.time.LocalDateTime

class User (
    val id: Long,
    val name: String,
    val address: String,
    val email: String,
    val mobilePhone: String,
    val createdAt: LocalDateTime
)
