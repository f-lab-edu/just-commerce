package com.justcommerce.user.controller.port

import com.justcommerce.user.domain.Cart

interface FindCartService {

    fun findAllByUserId(userId: Long): List<Cart>
}
