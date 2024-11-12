package com.justcommerce.payment.service.port

import com.justcommerce.payment.domain.User

interface FindUserRepository {

    fun findById(id: Long): User
}
