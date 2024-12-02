package com.justcommerce.payment.infrastructure.client

import com.justcommerce.payment.service.domain.Cart
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "item-service")
interface CartServiceClient {

    @GetMapping("/{userId}/carts/{cartId}")
    fun getCartByUserIdAndCartId(
        @PathVariable userId: Long,
        @PathVariable cartId: Long
    ): Cart
}
