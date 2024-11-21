package com.justcommerce.payment.service.port

import com.justcommerce.payment.service.domain.PaymentOrder
import com.justcommerce.payment.service.domain.PaymentStatus

interface CommandPaymentOrderRepository {

    fun save(order: PaymentOrder): PaymentOrder

    fun statusUpdate(orderId: String, status: PaymentStatus)
}
