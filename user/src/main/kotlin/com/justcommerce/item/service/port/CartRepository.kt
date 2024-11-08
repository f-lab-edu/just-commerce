package com.justcommerce.item.service.port

import com.justcommerce.item.domain.Cart

interface CartRepository {

    fun getById(id: Int): Cart?
}
