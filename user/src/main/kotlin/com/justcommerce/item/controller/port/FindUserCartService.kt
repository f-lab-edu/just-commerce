package com.justcommerce.item.controller.port

import com.justcommerce.item.domain.Cart

interface FindUserCartService {

    fun findCartByUserId(userId: Long): List<Cart>

    fun findCartByUserIdAndCartId(userId: Long, cartId: Long): Cart
}