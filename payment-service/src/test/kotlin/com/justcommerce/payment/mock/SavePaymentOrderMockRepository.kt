package com.justcommerce.payment.mock

import com.justcommerce.payment.service.domain.PaymentOrder
import com.justcommerce.payment.service.domain.PaymentStatus
import com.justcommerce.payment.service.port.SavePaymentOrderRepository

class SavePaymentOrderMockRepository: SavePaymentOrderRepository {

    override fun save(order: PaymentOrder): PaymentOrder {
        return PaymentOrder (
            id = order.id,
            buyerId = order.buyerId,
            orderName = order.orderName,
            paymentStatus = PaymentStatus.NOT_STARTED,
            items = order.items
        )
    }
}
