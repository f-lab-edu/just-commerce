package com.justcommerce.payment.infrastructure

import com.justcommerce.payment.domain.PaymentOrder
import com.justcommerce.payment.service.port.SavePaymentOrderRepository
import org.springframework.stereotype.Repository

@Repository
class PaymentEventRepository: SavePaymentOrderRepository {

    override fun save(event: PaymentOrder) {
        TODO("Not yet implemented")
    }
}
