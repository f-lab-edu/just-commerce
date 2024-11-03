package com.justcommerce.item.service

import com.justcommerce.common.domain.exception.CartNotFoundException
import com.justcommerce.item.controller.port.CartReader
import com.justcommerce.item.domain.Cart
import com.justcommerce.item.service.port.CartRepository
import org.springframework.stereotype.Service

@Service
class CartService (
    private val cartRepository: CartRepository
): CartReader {

    override fun getById(id: Int): Cart {
        return cartRepository.getById(id) ?: throw CartNotFoundException(id)
    }
}
