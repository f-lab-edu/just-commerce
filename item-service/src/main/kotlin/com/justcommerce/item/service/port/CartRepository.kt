package com.justcommerce.item.service.port

import com.justcommerce.item.service.domain.Cart

interface CartRepository {

    fun findCartById(cartId: Long): Cart

    fun findCartByUserId(userId: Long): List<Cart>

    fun findCartByUserIdAndCartId(userId: Long, cartId: Long): Cart
}
