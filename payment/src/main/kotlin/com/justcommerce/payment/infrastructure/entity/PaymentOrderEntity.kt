package com.justcommerce.payment.infrastructure.entity

import com.justcommerce.payment.domain.PaymentStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name= "payment_orders")
class PaymentOrderEntity (
    @Id
    @Column(name = "order_id")
    var id: String,

    @Column(name = "buyer_id", nullable = false)
    var buyerId: Long,

    @Column(name = "order_name", nullable = true)
    var orderName: String,

    @Column(name = "payment_status", nullable = false)
    @Enumerated(EnumType.STRING)
    var paymentStatus: PaymentStatus
)
