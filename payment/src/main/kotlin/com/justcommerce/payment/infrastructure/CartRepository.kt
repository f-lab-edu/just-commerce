package com.justcommerce.payment.infrastructure

import com.justcommerce.payment.domain.Cart
import com.justcommerce.payment.service.port.FindCartRepository
import org.springframework.stereotype.Repository

@Repository
class CartRepository: FindCartRepository {

    override fun getById(id: Long): Cart {
        TODO("Not yet implemented")
    }
}
