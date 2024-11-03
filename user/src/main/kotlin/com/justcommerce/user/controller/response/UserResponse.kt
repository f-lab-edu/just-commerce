package com.justcommerce.user.controller.response

import com.justcommerce.user.domain.User
import java.time.LocalDateTime

data class UserResponse(
    val id: Long,
    val name: String,
    val createdAt: LocalDateTime
) {

    companion object {
        fun from(user: User): UserResponse {
            return UserResponse(
                user.id,
                user.name,
                user.createdAt
            )
        }
    }
}
