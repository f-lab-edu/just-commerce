package com.justcommerce.payment.mock

import com.justcommerce.payment.domain.Cart
import com.justcommerce.payment.domain.Item
import com.justcommerce.payment.domain.User
import com.justcommerce.payment.infrastructure.webclient.UserServiceClient
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class MockUserServiceClient: UserServiceClient {

    private val items = listOf(
        Item("A", "camera", 30000, 10),
        Item("B", "rcVehicle", 100000, 10),
        Item("C", "toy", 150000, 3)
    )
    private val carts = mapOf(
        1L to Cart (1, 1, items)
    )
    private val users = mapOf(
        1L to User(1L, "테스트1", "서울", "01011111111"),
        2L to User(2L, "테스트2", "서울", "01011112222"),
    )

    override fun getUser(userId: Long): User {
        return users[userId] ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    override fun getCartByUserIdAndCartId(userId: Long, cartId: Long): Cart {
        val cart = carts.values.find { it.id == cartId } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        if (cart.userId != userId) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        return cart
    }
}
