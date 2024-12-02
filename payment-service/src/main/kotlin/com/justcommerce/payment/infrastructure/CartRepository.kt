package com.justcommerce.payment.infrastructure

import com.justcommerce.payment.infrastructure.client.CartServiceClient
import com.justcommerce.payment.service.domain.Cart
import com.justcommerce.payment.service.port.FindCartRepository
import org.springframework.stereotype.Repository

@Repository
class CartRepository (
    private val cartServiceClient: CartServiceClient
): FindCartRepository {

    override fun findCartByUserIdAndCartId(userId: Long, cartId: Long): Cart {
        return cartServiceClient.getCartByUserIdAndCartId(userId, cartId)
    }
}
