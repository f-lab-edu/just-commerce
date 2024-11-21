package com.justcommerce.payment.service

import com.justcommerce.payment.controller.port.ConfirmPaymentService
import com.justcommerce.payment.controller.port.ConfirmationCommand
import com.justcommerce.payment.controller.port.ConfirmationResult
import com.justcommerce.payment.service.domain.PaymentStatus
import com.justcommerce.payment.service.exception.PaymentConfirmationAmountNotMatchException
import com.justcommerce.payment.service.port.CommandPaymentOrderRepository
import com.justcommerce.payment.service.port.ExecutePaymentOrderRepository
import com.justcommerce.payment.service.port.FindPaymentOrderRepository
import org.springframework.stereotype.Service

@Service
class PaymentService (
    private val findPaymentOrderRepository: FindPaymentOrderRepository,
    private val commandPaymentOrderRepository: CommandPaymentOrderRepository,
    private val executePaymentOrderRepository: ExecutePaymentOrderRepository
): ConfirmPaymentService {

    override fun confirm(command: ConfirmationCommand): ConfirmationResult {
        val paymentOrder = findPaymentOrderRepository.findPaymentOrder(command.orderId)
        if (paymentOrder.calcAmount() != command.amount) {
            throw PaymentConfirmationAmountNotMatchException("체크아웃 주문 금액과 결제 승인 요청 주문 금액이 다릅니다. [${command.orderId}]")
        }

        commandPaymentOrderRepository.statusUpdate(command.orderId, PaymentStatus.EXECUTING)
        val result = executePaymentOrderRepository.execute(command)


        return result
    }
}
