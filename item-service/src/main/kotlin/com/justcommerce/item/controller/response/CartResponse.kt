package com.justcommerce.item.controller.response

import com.justcommerce.item.service.domain.Cart
import com.justcommerce.item.service.domain.Item

data class CartResponse (
    val id: Long,
    val items: List<Item>
) {
    companion object {
        fun from(cart: Cart): CartResponse {
            return CartResponse(
                cart.cartId,
                cart.items
            )
        }
    }
}
