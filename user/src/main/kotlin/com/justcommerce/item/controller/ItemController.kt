package com.justcommerce.item.controller

import com.justcommerce.item.controller.response.ItemResponse
import com.justcommerce.item.service.ItemService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/items")
class ItemController (
    private val itemService: ItemService
) {

    @GetMapping("/{id}")
    fun getItemById(@PathVariable id: String): ItemResponse {
        return ItemResponse.from(itemService.getById(id))
    }
}
