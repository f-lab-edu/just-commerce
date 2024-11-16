package com.justcommerce.item.service.port

import com.justcommerce.item.service.domain.Item

interface ItemRepository {

    fun getById(id: String): Item?
}
