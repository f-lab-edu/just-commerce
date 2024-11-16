package com.justcommerce.user.service

import com.justcommerce.common.domain.exception.UserNotFoundException
import com.justcommerce.user.controller.port.FindUserService
import com.justcommerce.user.domain.User
import com.justcommerce.user.service.port.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
): FindUserService {

    override fun findById(id: Long): User {
        return userRepository.findById(id) ?: throw UserNotFoundException(id)
    }

    override fun findAll(): List<User> {
        return userRepository.findAll()
    }
}
