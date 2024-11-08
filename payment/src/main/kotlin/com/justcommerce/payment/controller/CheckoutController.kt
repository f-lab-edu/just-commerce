package com.justcommerce.payment.controller

import com.justcommerce.payment.controller.request.CheckoutRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/checkout")
class CheckoutController {

    @GetMapping
    fun checkoutPage(request: CheckoutRequest, model: Model): String {
        // TODO CheckoutRequest 정보를 기반으로 Payment 정보 (Payment 1 : PaymentOrder N 관계) DB 저장
        val orderId = 1
        val amount = 1000

        model.apply {
            this.addAttribute("orderId", orderId)
            this.addAttribute("amount", amount)
        }
        return "/widget/checkout"
    }
}
