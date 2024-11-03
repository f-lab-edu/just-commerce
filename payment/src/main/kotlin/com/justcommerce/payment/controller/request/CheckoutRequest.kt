package com.justcommerce.payment.controller.request

import com.justcommerce.common.util.IdempotencyCreator
import java.time.LocalDateTime

data class CheckoutRequest (
    val cartId: Long,
    val buyerId: Long,
    val productIds: List<String>
) {
    val idempotencyKey: String = IdempotencyCreator.create(this)
}
