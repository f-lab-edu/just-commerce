package com.justcommerce.user.controller.port

import com.justcommerce.user.domain.User

interface FindUserService {

    fun findById(id: Long): User
    fun findAll(): List<User>
}
