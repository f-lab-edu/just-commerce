package com.justcommerce.item.service

import com.justcommerce.common.domain.exception.CartNotFoundException
import com.justcommerce.item.controller.port.FindCartService
import com.justcommerce.item.controller.port.FindUserCartService
import com.justcommerce.item.domain.Cart
import com.justcommerce.item.service.port.CartRepository
import org.springframework.stereotype.Service

@Service
class CartService (
    private val cartRepository: CartRepository
): FindCartService, FindUserCartService {

    override fun findById(id: Long): Cart {
        return cartRepository.getById(id) ?: throw CartNotFoundException(id)
    }

    override fun findCartByUserId(userId: Long): List<Cart> {
        return cartRepository.findCartByUserId(userId)
    }
}
