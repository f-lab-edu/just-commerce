package com.justcommerce.user.controller.port

import com.justcommerce.user.domain.User

interface UserReader {

    fun getById(id: Long): User
}
