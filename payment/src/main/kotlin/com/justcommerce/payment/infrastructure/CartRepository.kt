package com.justcommerce.payment.infrastructure

import com.justcommerce.payment.domain.Cart
import com.justcommerce.payment.infrastructure.webclient.UserServiceClient
import com.justcommerce.payment.service.port.FindCartRepository
import org.springframework.stereotype.Repository

@Repository
class CartRepository (
    private val userServiceClient: UserServiceClient
): FindCartRepository {

    override fun findCartByUserIdAndCartId(userId: Long, cartId: Long): Cart {
        return userServiceClient.getCartByUserIdAndCartId(userId, cartId)
    }
}
