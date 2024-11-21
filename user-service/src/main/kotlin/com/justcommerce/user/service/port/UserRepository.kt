package com.justcommerce.user.service.port

import com.justcommerce.user.service.domain.User

interface UserRepository {

    fun findById(id: Long): User

    fun findAll(): List<User>
}
