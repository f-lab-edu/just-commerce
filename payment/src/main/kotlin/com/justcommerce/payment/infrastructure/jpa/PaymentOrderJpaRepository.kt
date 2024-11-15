package com.justcommerce.payment.infrastructure.jpa

import com.justcommerce.payment.infrastructure.entity.PaymentOrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentOrderJpaRepository: JpaRepository<PaymentOrderEntity, String>
