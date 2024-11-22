package com.justcommerce.payment.infrastructure

import com.justcommerce.payment.controller.port.ConfirmationCommand
import com.justcommerce.payment.controller.port.ConfirmationResult
import com.justcommerce.payment.infrastructure.client.TossPaymentClient
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
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.Base64

@Repository
class PaymentOrderRepository (
    private val paymentOrderJpaRepository: PaymentOrderJpaRepository,
    private val paymentOrderItemJpaRepository: PaymentOrderItemJpaRepository,
    private val tossPaymentClient: TossPaymentClient,
    @Value("\${PG.toss.secretKey}") private val secretKey: String
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
        val encodedSecretKey = Base64.getEncoder().encodeToString(("$secretKey:").toByteArray())

        val response = tossPaymentClient.confirm(
            body = command,
            idempotencyKey = command.orderId,
            secretKey = "Basic $encodedSecretKey"
        )

        // TODO 결제 승인 결과에 따른 핸들링
        // - 결제 승인 실패 시 결제 승인 재요청 전략 수립 필요
        // - 결제 상태 업데이트 (성공이라면 장부와 판매자 지갑에도 업데이트 필요)

        return ConfirmationResult(
            PaymentStatus.SUCCESS,
            "test",
            null
        )
    }
}
