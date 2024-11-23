package com.justcommerce.payment.infrastructure

import com.justcommerce.payment.service.domain.User
import com.justcommerce.payment.infrastructure.webclient.UserServiceClient
import com.justcommerce.payment.service.port.FindUserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepository (
    private val userServiceClient: UserServiceClient
): FindUserRepository {

    override fun findUserById(id: Long): User {
        return userServiceClient.getUser(id)
    }
}
