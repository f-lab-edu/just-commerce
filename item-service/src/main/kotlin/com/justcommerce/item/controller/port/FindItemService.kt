package com.justcommerce.item.controller.port

import com.justcommerce.item.service.domain.Item

interface FindItemService {

    fun findById(id: String): Item
}
