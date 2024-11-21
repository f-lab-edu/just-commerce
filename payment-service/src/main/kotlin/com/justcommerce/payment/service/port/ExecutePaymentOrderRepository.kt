package com.justcommerce.payment.service.port

import com.justcommerce.payment.controller.port.ConfirmationCommand
import com.justcommerce.payment.controller.port.ConfirmationResult

interface ExecutePaymentOrderRepository {

    fun execute(command: ConfirmationCommand): ConfirmationResult
}
