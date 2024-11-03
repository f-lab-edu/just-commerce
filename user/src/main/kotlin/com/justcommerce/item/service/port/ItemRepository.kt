package com.justcommerce.item.service.port

import com.justcommerce.item.domain.Item

interface ItemRepository {

    fun getById(id: String): Item?
}
