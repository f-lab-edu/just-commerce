package com.justcommerce.payment.controller

import com.justcommerce.payment.controller.port.ConfirmPaymentService
import com.justcommerce.payment.controller.port.ConfirmationCommand
import com.justcommerce.payment.controller.request.TossPaymentConfirmRequest
import com.justcommerce.payment.controller.response.TossPaymentConfirmResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PaymentController (
    private val confirmPaymentService: ConfirmPaymentService
) {

    @PostMapping("/confirm/widget")
    fun confirm(@RequestBody request: TossPaymentConfirmRequest): TossPaymentConfirmResponse {
        val command = ConfirmationCommand (
            request.paymentKey,
            request.orderId,
            request.amount
        )
        confirmPaymentService.confirm(command)

        return TossPaymentConfirmResponse("OK", "")
    }
}
