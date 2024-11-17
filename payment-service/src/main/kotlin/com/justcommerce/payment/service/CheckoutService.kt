package com.justcommerce.payment.service

import com.justcommerce.payment.controller.port.CheckoutCommand
import com.justcommerce.payment.controller.port.CheckoutResult
import com.justcommerce.payment.controller.port.CreateCheckoutService
import com.justcommerce.payment.service.domain.PaymentOrder
import com.justcommerce.payment.service.domain.PaymentStatus
import com.justcommerce.payment.service.port.FindCartRepository
import com.justcommerce.payment.service.port.FindUserRepository
import com.justcommerce.payment.service.port.SavePaymentOrderRepository
import org.springframework.stereotype.Service

@Service
class CheckoutService (
    private val findCartRepository: FindCartRepository,
    private val findUserRepository: FindUserRepository,
    private val savePaymentOrderRepository: SavePaymentOrderRepository
): CreateCheckoutService {

    override fun create(checkoutCommand: CheckoutCommand): CheckoutResult {
        val user = findUserRepository.findUserById(checkoutCommand.userId)
        val cart = findCartRepository.findCartByUserIdAndCartId(checkoutCommand.userId, checkoutCommand.cartId)

        val paymentOrder = PaymentOrder(
            id = checkoutCommand.idempotencyKey,
            buyerId = user.id,
            orderName = cart.extractCartName(),
            paymentStatus = PaymentStatus.NOT_STARTED,
            items = cart.toPaymentOrderItems(checkoutCommand.idempotencyKey)
        )
        val savedPaymentOrder = savePaymentOrderRepository.save(paymentOrder)

        return CheckoutResult(
            orderId = savedPaymentOrder.id,
            orderName = savedPaymentOrder.orderName,
            amount = savedPaymentOrder.calcAmount(),
            customerName = user.name,
            customerEmail = user.email,
            customerMobilePhone = user.mobilePhone
        )
    }
}
