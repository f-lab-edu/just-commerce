package com.justcommerce.payment.service.port

import com.justcommerce.payment.domain.PaymentOrder

interface SavePaymentOrderRepository {

    fun save(order: PaymentOrder): PaymentOrder
}
