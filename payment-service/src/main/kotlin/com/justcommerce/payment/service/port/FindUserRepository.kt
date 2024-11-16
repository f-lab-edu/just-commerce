package com.justcommerce.payment.service.port

import com.justcommerce.payment.service.domain.User

interface FindUserRepository {

    fun findUserById(id: Long): User
}
