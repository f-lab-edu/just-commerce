package com.justcommerce.item.infrastructure

import com.justcommerce.item.domain.Cart
import com.justcommerce.item.domain.Category
import com.justcommerce.item.domain.Item
import com.justcommerce.item.domain.Price
import com.justcommerce.item.service.port.CartRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class InMemoryCartRepositoryImpl: CartRepository {

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

    private val items = listOf(
        Item("A", "camera", camera, Price("9.99", "USD"), 1, LocalDateTime.now()),
        Item("B", "rcVehicle", rcVehicle, Price("15.99", "USD"), 2, LocalDateTime.now()),
        Item("C", "toy", toy, Price("150000", "KRW"), 3, LocalDateTime.now())
    )

    private val carts = mapOf(1 to Cart(1, items))

    override fun getById(id: Int): Cart? {
        return carts[id]
    }
}
