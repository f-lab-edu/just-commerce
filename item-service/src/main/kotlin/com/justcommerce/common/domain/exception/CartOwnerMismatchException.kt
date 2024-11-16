package com.justcommerce.common.domain.exception

class CartOwnerMismatchException(message: String) : RuntimeException(message) {

    constructor(id: Long): this("카트의 주인이 아닙니다. [$id]")
}
