package com.justcommerce.item.controller.response

import com.justcommerce.item.domain.Cart

data class UserCartResponse (
    val cartId: Long,
    val userId: Long,
    val items: List<ItemResponse>
) {
    companion object {
        fun from(cart: Cart) = UserCartResponse (
            cartId = cart.cartId,
            userId = cart.userId,
            items = cart.items.map(ItemResponse::from)
        )
    }
}
