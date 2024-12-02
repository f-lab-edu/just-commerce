package com.justcommerce.item.infrastructure

import com.justcommerce.item.infrastructure.exception.ItemNotFoundException
import com.justcommerce.common.holder.ClockHolder
import com.justcommerce.item.service.domain.Category
import com.justcommerce.item.service.domain.Item
import com.justcommerce.item.service.port.ItemRepository
import org.springframework.stereotype.Repository

@Repository
class InMemoryItemRepositoryImpl (
    clockHolder: ClockHolder
): ItemRepository {

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
        "A" to Item("A", "camera", camera, 100000, 1, clockHolder.now()),
        "B" to Item("B", "rcVehicle", rcVehicle, 150000, 2, clockHolder.now().minusDays(1)),
        "C" to Item("C", "toy", toy, 30000, 3, clockHolder.now().minusDays(2))
    )

    override fun getItemById(id: String): Item {
        return items[id] ?: throw ItemNotFoundException(id)
    }
}
