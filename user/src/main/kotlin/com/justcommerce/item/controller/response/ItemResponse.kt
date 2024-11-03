package com.justcommerce.item.controller.response

import com.justcommerce.item.domain.Category
import com.justcommerce.item.domain.Item
import com.justcommerce.item.domain.Price

data class ItemResponse (
    val id: String,
    val title: String,
    val categories: List<Category>,
    val price: Price,
    val sellerId: Long,
) {
    companion object {
        fun from(item: Item): ItemResponse {
            return ItemResponse(
                item.id,
                item.title,
                item.categories,
                item.price,
                item.sellerId
            )
        }
    }
}
