package com.justcommerce.payment.domain

data class PaymentOrder (
    val id: String,
    val buyerId: Long,
    val orderName: String,
    val paymentStatus: PaymentStatus,
    val items: List<PaymentOrderItem>
) {
    /**
     * 가격의 타입은 BigDecimal?
     */
    fun calcAmount() = items.sumOf { it.price }
}
