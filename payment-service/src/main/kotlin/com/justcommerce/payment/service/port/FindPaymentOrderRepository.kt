package com.justcommerce.payment.service.port

import com.justcommerce.payment.service.domain.PaymentOrder

interface FindPaymentOrderRepository {

    fun findPaymentOrder(orderId: String): PaymentOrder
}
