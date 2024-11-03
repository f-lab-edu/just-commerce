package com.justcommerce.item.controller.port

import com.justcommerce.item.domain.Cart

interface CartReader {

    fun getById(id: Int): Cart
}
