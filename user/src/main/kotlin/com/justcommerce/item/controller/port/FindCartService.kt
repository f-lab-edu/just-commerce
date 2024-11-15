package com.justcommerce.item.controller.port

import com.justcommerce.item.domain.Cart

interface FindCartService {

    fun findCartById(id: Long): Cart
}
