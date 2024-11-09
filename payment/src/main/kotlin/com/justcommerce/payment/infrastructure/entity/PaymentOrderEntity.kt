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
    private var id: String,

    @Column(name = "buyer_id")
    private var buyerId: Long,

    @Column(name = "order_name")
    private var orderName: String,

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private var paymentStatus: PaymentStatus


)
