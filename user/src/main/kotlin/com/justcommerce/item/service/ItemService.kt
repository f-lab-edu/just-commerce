package com.justcommerce.item.service

import com.justcommerce.common.domain.exception.ItemNotFoundException
import com.justcommerce.item.controller.port.ItemReader
import com.justcommerce.item.domain.Item
import com.justcommerce.item.service.port.ItemRepository
import org.springframework.stereotype.Service

@Service
class ItemService(
    private val itemRepository: ItemRepository
): ItemReader {

    override fun getById(id: String): Item {
        return itemRepository.getById(id) ?: throw ItemNotFoundException(id)
    }
}
