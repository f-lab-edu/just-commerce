package com.justcommerce.item.controller.port

import com.justcommerce.item.domain.Item

interface FindItemService {

    fun findById(id: String): Item
}
