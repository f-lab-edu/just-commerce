package com.justcommerce.common.domain.exception

class UserNotFoundException(message: String) : RuntimeException(message) {

    constructor(id: Long): this("사용자를 찾을 수 없습니다. [$id]")
}
