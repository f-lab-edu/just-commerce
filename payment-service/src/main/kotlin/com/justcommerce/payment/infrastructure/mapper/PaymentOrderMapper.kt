package com.justcommerce.payment.infrastructure.mapper

import com.justcommerce.payment.service.domain.PaymentOrder
import com.justcommerce.payment.service.domain.PaymentOrderItem
import com.justcommerce.payment.infrastructure.jpa.entity.PaymentOrderEntity
import com.justcommerce.payment.infrastructure.jpa.entity.PaymentOrderItemEntity

fun PaymentOrder.toEntity() = PaymentOrderEntity (
    id = this.id,
    buyerId = this.buyerId,
    orderName = this.orderName,
    paymentStatus = this.paymentStatus
)

fun PaymentOrderItem.toEntity() = PaymentOrderItemEntity (
    itemId = this.itemId,
    orderId = this.orderId,
    sellerId = this.sellerId,
    price = this.price
)

fun PaymentOrderEntity.toDomain(items: List<PaymentOrderItemEntity>) = PaymentOrder (
    this.id,
    this.buyerId,
    this.orderName,
    this.paymentStatus,
    items.map { it.toDomain() }
)

fun PaymentOrderItemEntity.toDomain() = PaymentOrderItem (
    this.itemId,
    this.orderId,
    this.sellerId,
    this.price
)
