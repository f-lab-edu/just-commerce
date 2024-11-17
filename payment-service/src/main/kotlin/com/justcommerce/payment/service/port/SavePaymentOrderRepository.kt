package com.justcommerce.payment.service.port

import com.justcommerce.payment.service.domain.PaymentOrder

interface SavePaymentOrderRepository {

    fun save(order: PaymentOrder): PaymentOrder
}
