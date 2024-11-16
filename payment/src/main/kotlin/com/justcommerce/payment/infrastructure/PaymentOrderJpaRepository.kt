package com.justcommerce.payment.infrastructure

import com.justcommerce.payment.infrastructure.entity.PaymentOrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentOrderJpaRepository: JpaRepository<PaymentOrderEntity, String>
