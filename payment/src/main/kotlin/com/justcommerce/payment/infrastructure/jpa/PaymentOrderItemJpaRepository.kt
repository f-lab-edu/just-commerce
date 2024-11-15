package com.justcommerce.payment.infrastructure.jpa

import com.justcommerce.payment.infrastructure.entity.PaymentOrderItemEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentOrderItemJpaRepository: JpaRepository<PaymentOrderItemEntity, Long> {

    fun findAllByOrderId(orderId: String): List<PaymentOrderItemEntity>
}
