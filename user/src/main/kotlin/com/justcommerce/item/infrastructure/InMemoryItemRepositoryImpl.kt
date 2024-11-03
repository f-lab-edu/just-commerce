package com.justcommerce.item.infrastructure

import com.justcommerce.item.domain.Category
import com.justcommerce.item.domain.Item
import com.justcommerce.item.domain.Price
import com.justcommerce.item.service.port.ItemRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class InMemoryItemRepositoryImpl: ItemRepository {

    private val camera = listOf(
        Category("179697", "Camera Drones"),
        Category("625", "Cameras & Photo"),
    )
    private val rcVehicle = listOf(
        Category("182186", "Other RC Model Vehicles & Kits"),
        Category("2562", "Radio Control & Control Line"),
        Category("182181", "RC Model Vehicles & Kits")
    )
    private val toy = listOf(Category("220", "Toys & Hobbies"),)

    private val items = mapOf(
        "A" to Item("A", "camera", camera, Price("9.99", "USD"), 1, LocalDateTime.now()),
        "B" to Item("B", "rcVehicle", rcVehicle, Price("15.99", "USD"), 2, LocalDateTime.now()),
        "C" to Item("C", "toy", toy, Price("150000", "KRW"), 3, LocalDateTime.now())
    )

    override fun getById(id: String): Item? {
        return items[id]
    }
}