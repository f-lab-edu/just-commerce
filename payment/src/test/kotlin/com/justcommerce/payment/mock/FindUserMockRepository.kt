package com.justcommerce.payment.mock

import com.justcommerce.payment.domain.User
import com.justcommerce.payment.service.port.FindUserRepository

class FindUserMockRepository: FindUserRepository {

    private val users = mapOf(
        1L to User(1L, "테스트1", "서울", "01011111111"),
        2L to User(2L, "테스트2", "서울", "01011112222"),
    )

    override fun findById(id: Long): User {
        return users[id] ?: throw IllegalArgumentException()
    }
}
