package com.justcommerce.payment.infrastructure.jpa.entity

import com.justcommerce.common.infrastructure.AuditEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    name= "payment_order_items",
    indexes = [Index(name = "order_id_index", columnList = "order_id")]
)
class PaymentOrderItemEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "item_id", nullable = false)
    var itemId: String,

    @Column(name = "order_id", nullable = false)
    var orderId: String,

    @Column(name = "seller_id", nullable = false)
    var sellerId: Long,

    @Column(name = "price", nullable = false)
    var price: Long
): AuditEntity()
