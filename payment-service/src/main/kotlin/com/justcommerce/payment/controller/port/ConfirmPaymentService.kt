package com.justcommerce.payment.controller.port

interface ConfirmPaymentService {

    fun confirm(command: ConfirmationCommand): ConfirmationResult
}
