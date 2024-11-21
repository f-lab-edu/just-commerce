package com.justcommerce.payment.infrastructure

import com.justcommerce.payment.controller.port.ConfirmationCommand
import com.justcommerce.payment.controller.port.ConfirmationResult
import com.justcommerce.payment.infrastructure.exception.PaymentOrderNotFoundException
import com.justcommerce.payment.service.domain.PaymentOrder
import com.justcommerce.payment.infrastructure.jpa.entity.PaymentOrderItemEntity
import com.justcommerce.payment.infrastructure.jpa.PaymentOrderItemJpaRepository
import com.justcommerce.payment.infrastructure.jpa.PaymentOrderJpaRepository
import com.justcommerce.payment.infrastructure.mapper.toDomain
import com.justcommerce.payment.infrastructure.mapper.toEntity
import com.justcommerce.payment.service.domain.PaymentStatus
import com.justcommerce.payment.service.port.FindPaymentOrderRepository
import com.justcommerce.payment.service.port.CommandPaymentOrderRepository
import com.justcommerce.payment.service.port.ExecutePaymentOrderRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class PaymentOrderRepository (
    private val paymentOrderJpaRepository: PaymentOrderJpaRepository,
    private val paymentOrderItemJpaRepository: PaymentOrderItemJpaRepository
): CommandPaymentOrderRepository, FindPaymentOrderRepository, ExecutePaymentOrderRepository {

    override fun save(order: PaymentOrder): PaymentOrder {
        val orderEntity = order.toEntity()
        val savedOrderEntity = paymentOrderJpaRepository.save(orderEntity)
        val savedOrderItemEntities: List<PaymentOrderItemEntity> = paymentOrderItemJpaRepository.saveAll(order.items.map { it.toEntity() })

        return savedOrderEntity.toDomain(savedOrderItemEntities)
    }

    override fun statusUpdate(orderId: String, status: PaymentStatus) {
        val orderEntity = paymentOrderJpaRepository.findByIdOrNull(orderId)
            ?: throw PaymentOrderNotFoundException(orderId)
        orderEntity.paymentStatus = status
        paymentOrderJpaRepository.save(orderEntity)
    }

    override fun findPaymentOrder(orderId: String): PaymentOrder {
        val orderEntity = paymentOrderJpaRepository.findByIdOrNull(orderId)
            ?: throw PaymentOrderNotFoundException(orderId)
        val orderItemEntities = paymentOrderItemJpaRepository.findAllByOrderId(orderId).ifEmpty {
            throw PaymentOrderNotFoundException(orderId)
        }

        return orderEntity.toDomain(orderItemEntities)
    }

    override fun execute(command: ConfirmationCommand): ConfirmationResult {
        TODO("Not yet implemented")
    }
}
