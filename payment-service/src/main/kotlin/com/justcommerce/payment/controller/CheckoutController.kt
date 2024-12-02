package com.justcommerce.payment.controller

import com.justcommerce.payment.controller.port.CheckoutResult
import com.justcommerce.payment.controller.request.CreateCheckoutRequest
import com.justcommerce.payment.controller.port.CreateCheckoutService
import com.justcommerce.payment.controller.port.CheckoutCommand
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/checkout")
class CheckoutController (
    private val createCheckoutService: CreateCheckoutService
) {

    @PostMapping
    fun checkout(@RequestBody @Validated createCheckoutRequest: CreateCheckoutRequest): CheckoutResult {
        val checkoutCommand = CheckoutCommand(
            createCheckoutRequest.userId,
            createCheckoutRequest.cartId
        )
        return createCheckoutService.create(checkoutCommand)
    }
}
