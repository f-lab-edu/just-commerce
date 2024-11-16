package com.justcommerce.item.controller

import com.justcommerce.item.controller.port.FindUserCartService
import com.justcommerce.item.controller.response.UserCartResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserCartController (
    private val findUserCartService: FindUserCartService
) {

    @GetMapping("/{userId}/carts")
    fun findUserCarts(@PathVariable userId: Long): List<UserCartResponse> {
        return findUserCartService.findCartByUserId(userId)
            .map(UserCartResponse::from)
    }
}
