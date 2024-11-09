package com.justcommerce.payment.domain

data class Cart (
    val id: Long,
    val userId: Long,
    val items: List<Item>
) {

    fun extractCartName(): String {
        return items.joinToString(limit = 1, separator = "", truncated = "", postfix = " 외 ${items.size - 1}건") { it.title }
    }

    /**
     * 가격의 타입은 BigDecimal?
     */
    fun calcAmount() = items.sumOf { it.price.toLong() }

    fun toPaymentOrderItems(orderId: String) = this.items.map {
        PaymentOrderItem(
            itemId = it.id,
            orderId = orderId,
            sellerId = it.sellerId,
            price = it.price.toLong()
        )
    }
}
