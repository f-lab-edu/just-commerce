package com.justcommerce.payment.service

import com.justcommerce.payment.controller.port.CheckoutCommand
import com.justcommerce.payment.controller.port.CheckoutResult
import com.justcommerce.payment.controller.port.CreateCheckoutService
import com.justcommerce.payment.domain.PaymentOrder
import com.justcommerce.payment.domain.PaymentStatus
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
        // TODO
        // 1. 사용자 정보 조회
        val user = findUserRepository.getById(checkoutCommand.userId)
        // 2. 구매 물품 조회
        val cart = findCartRepository.getById(checkoutCommand.cartId)

        val paymentOrder = PaymentOrder(
            id = checkoutCommand.idempotencyKey,
            buyerId = user.id,
            orderName = cart.extractCartName(),
            paymentStatus = PaymentStatus.NOT_STARTED,
            items = cart.toPaymentOrderItems(checkoutCommand.idempotencyKey)
        )
        // 3. (PaymentOrder 1 : PaymentOrderItem N 관계) DB 저장
        savePaymentOrderRepository.save(paymentOrder)

        return CheckoutResult(
            orderId = paymentOrder.id,
            amount = cart.calcAmount(),
            customerName = user.name,
            customerEmail = user.email,
            customerMobilePhone = user.mobilePhone
        )
    }
}
