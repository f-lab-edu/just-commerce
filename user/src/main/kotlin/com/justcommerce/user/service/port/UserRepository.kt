package com.justcommerce.user.service.port

import com.justcommerce.user.domain.User

interface UserRepository {

    fun findById(id: Long): User?

    fun findAll(): List<User>
}
