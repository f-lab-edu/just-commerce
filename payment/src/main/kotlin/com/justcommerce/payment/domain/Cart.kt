package com.justcommerce.payment.domain

data class Cart (
    val id: Long,
    val userId: Long,
    val items: List<Item>
) {

    fun extractCartName(): String {
        return items.joinToString(limit = 1, separator = "", truncated = "", postfix = " 외 ${items.size - 1}건") { it.title }
    }

    fun toPaymentOrderItems(orderId: String) = this.items.map {
        PaymentOrderItem(
            itemId = it.id,
            orderId = orderId,
            sellerId = it.sellerId,
            price = it.price.toLong()
        )
    }
}
