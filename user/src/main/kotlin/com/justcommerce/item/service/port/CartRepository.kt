package com.justcommerce.item.service.port

import com.justcommerce.item.domain.Cart

interface CartRepository {

    fun getById(cartId: Long): Cart?

    fun findCartByUserId(userId: Long): List<Cart>
}
