package com.justcommerce.item.service

import com.justcommerce.item.controller.port.FindCartService
import com.justcommerce.item.controller.port.FindUserCartService
import com.justcommerce.item.service.domain.Cart
import com.justcommerce.item.service.port.CartRepository
import org.springframework.stereotype.Service

@Service
class CartService (
    private val cartRepository: CartRepository
): FindCartService, FindUserCartService {

    override fun findCartById(id: Long): Cart {
        return cartRepository.findCartById(id)
    }

    override fun findCartByUserId(userId: Long): List<Cart> {
        return cartRepository.findCartByUserId(userId)
    }

    override fun findCartByUserIdAndCartId(userId: Long, cartId: Long): Cart {
        return cartRepository.findCartByUserIdAndCartId(userId, cartId)
    }
}
