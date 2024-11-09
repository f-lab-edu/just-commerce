package com.justcommerce.payment.controller

import com.justcommerce.payment.controller.port.CheckoutCommand
import com.justcommerce.payment.controller.port.CreateCheckoutService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class CheckoutController (
    private val createCheckoutService: CreateCheckoutService
) {

    /**
     * 시나리오 실행 편의를 위해 GET 메서드를 사용
     */
    @GetMapping("/checkout/{userId}/{cartId}")
    fun checkout(
        @PathVariable("userId") userId: Long,
        @PathVariable("cartId") cartId: Long,
        model: Model
    ): String {
        val checkoutCommand = CheckoutCommand(userId, cartId)
        val checkoutResult = createCheckoutService.create(checkoutCommand)

        model.apply {
            this.addAttribute("orderId", checkoutResult.orderId)
            this.addAttribute("amount", checkoutResult.amount)
            this.addAttribute("customerName", checkoutResult.customerName)
            this.addAttribute("customerEmail", checkoutResult.customerEmail)
            this.addAttribute("customerMobilePhone", checkoutResult.customerMobilePhone)
        }
        return "widget/checkout"
    }
}
