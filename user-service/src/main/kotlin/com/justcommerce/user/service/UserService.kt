package com.justcommerce.user.service

import com.justcommerce.user.controller.port.FindUserService
import com.justcommerce.user.service.domain.User
import com.justcommerce.user.service.port.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
): FindUserService {

    override fun findById(id: Long): User {
        return userRepository.findById(id)
    }

    override fun findAll(): List<User> {
        return userRepository.findAll()
    }
}
