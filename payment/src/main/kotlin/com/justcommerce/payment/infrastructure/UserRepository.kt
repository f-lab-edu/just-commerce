package com.justcommerce.payment.infrastructure

import com.justcommerce.payment.domain.User
import com.justcommerce.payment.service.port.FindUserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepository: FindUserRepository {

    override fun getById(id: Long): User {
        TODO("Not yet implemented")
    }
}
