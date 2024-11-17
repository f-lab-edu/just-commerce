package com.justcommerce.item.controller.port

import com.justcommerce.item.service.domain.Cart

interface FindCartService {

    fun findCartById(id: Long): Cart
}
