package com.justcommerce.payment.infrastructure.webclient

import com.justcommerce.payment.domain.Cart
import com.justcommerce.payment.domain.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "user")
interface UserServiceClient {

    @GetMapping("/users/{userId}")
    fun getUser(@PathVariable userId: Long): User

    @GetMapping("/{userId}/carts/{cartId}")
    fun getCartByUserIdAndCartId(
        @PathVariable userId: Long,
        @PathVariable cartId: Long
    ): Cart
}
