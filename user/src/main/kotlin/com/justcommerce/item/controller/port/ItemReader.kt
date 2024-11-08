package com.justcommerce.item.controller.port

import com.justcommerce.item.domain.Item

interface ItemReader {

    fun getById(id: String): Item
}
