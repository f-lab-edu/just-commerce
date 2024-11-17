package com.justcommerce.payment.infrastructure

import com.justcommerce.payment.service.domain.PaymentOrder
import com.justcommerce.payment.infrastructure.jpa.entity.PaymentOrderItemEntity
import com.justcommerce.payment.infrastructure.jpa.PaymentOrderItemJpaRepository
import com.justcommerce.payment.infrastructure.jpa.PaymentOrderJpaRepository
import com.justcommerce.payment.infrastructure.mapper.toDomain
import com.justcommerce.payment.infrastructure.mapper.toEntity
import com.justcommerce.payment.service.port.SavePaymentOrderRepository
import org.springframework.stereotype.Repository

@Repository
class PaymentOrderRepository (
    private val paymentOrderJpaRepository: PaymentOrderJpaRepository,
    private val paymentOrderItemJpaRepository: PaymentOrderItemJpaRepository
): SavePaymentOrderRepository {

    override fun save(order: PaymentOrder): PaymentOrder {
        val orderEntity = order.toEntity()
        val savedOrderEntity = paymentOrderJpaRepository.save(orderEntity)
        val savedOrderItemEntities: List<PaymentOrderItemEntity> = paymentOrderItemJpaRepository.saveAll(order.items.map { it.toEntity() })

        return savedOrderEntity.toDomain(savedOrderItemEntities)
    }
}
