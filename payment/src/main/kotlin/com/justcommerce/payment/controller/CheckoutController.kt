package com.justcommerce.payment.controller

import com.justcommerce.payment.controller.port.CheckoutCommand
import com.justcommerce.payment.controller.port.CheckoutResult
import com.justcommerce.payment.controller.port.CreateCheckoutService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckoutController (
    private val createCheckoutService: CreateCheckoutService
) {

    @PostMapping("/checkout")
    fun checkout(
        @RequestBody checkoutCommand: CheckoutCommand
    ): CheckoutResult {
        return createCheckoutService.create(checkoutCommand)
    }
}
