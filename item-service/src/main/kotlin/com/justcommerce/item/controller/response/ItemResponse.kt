package com.justcommerce.item.controller.response

import com.justcommerce.item.service.domain.Category
import com.justcommerce.item.service.domain.Item

data class ItemResponse (
    val id: String,
    val title: String,
    val categories: List<Category>,
    val price: Long,
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
