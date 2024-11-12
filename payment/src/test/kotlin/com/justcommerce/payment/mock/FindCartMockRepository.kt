package com.justcommerce.payment.mock

import com.justcommerce.payment.domain.Cart
import com.justcommerce.payment.domain.Item
import com.justcommerce.payment.service.port.FindCartRepository

class FindCartMockRepository: FindCartRepository {

    private val items = listOf(
        Item("A", "camera", "30000", 10),
        Item("B", "rcVehicle", "100000", 10),
        Item("C", "toy", "150000", 3)
    )
    private val map = mapOf(
        1L to Cart (1, 1, items)
    )

    override fun findById(id: Long): Cart {
        return map[id] ?: throw IllegalArgumentException()
    }
}
