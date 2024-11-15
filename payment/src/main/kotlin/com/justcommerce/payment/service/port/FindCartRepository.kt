package com.justcommerce.payment.service.port

import com.justcommerce.payment.domain.Cart

interface FindCartRepository {

    fun findCartByUserIdAndCartId(userId: Long, cartId: Long): Cart
}
