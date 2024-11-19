package com.justcommerce.payment.controller

import com.justcommerce.payment.controller.port.CheckoutCommand
import com.justcommerce.payment.controller.port.CheckoutResult
import com.justcommerce.payment.controller.port.CreateCheckoutService
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
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
    fun checkout(@RequestBody @Validated checkoutCommand: CheckoutCommand): CheckoutResult {
        return createCheckoutService.create(checkoutCommand)
    }
}
