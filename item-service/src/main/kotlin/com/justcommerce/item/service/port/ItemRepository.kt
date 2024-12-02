package com.justcommerce.item.service.port

import com.justcommerce.item.service.domain.Item

interface ItemRepository {

    fun getItemById(id: String): Item
}
