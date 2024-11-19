package com.justcommerce.item.infrastructure.exception

import com.justcommerce.common.exception.ResourceNotFoundException

class CartOwnerMismatchException(message: String) : ResourceNotFoundException(message) {

    constructor(id: Long): this("카트의 주인이 아닙니다. [$id]")
}
