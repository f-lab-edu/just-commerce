package com.justcommerce.item.service

import com.justcommerce.item.controller.port.FindItemService
import com.justcommerce.item.service.domain.Item
import com.justcommerce.item.service.port.ItemRepository
import org.springframework.stereotype.Service

@Service
class ItemService(
    private val itemRepository: ItemRepository
): FindItemService {

    override fun findById(id: String): Item {
        return itemRepository.getItemById(id)
    }
}
