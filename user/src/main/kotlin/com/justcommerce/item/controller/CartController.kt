package com.justcommerce.item.controller

import com.justcommerce.item.controller.port.FindCartService
import com.justcommerce.item.controller.response.CartResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/carts")
class CartController (
    private val findCartService: FindCartService
) {

    @GetMapping("/{id}")
    fun getCartById(@PathVariable id: Long): CartResponse {
        return CartResponse.from(findCartService.findById(id))
    }
}
