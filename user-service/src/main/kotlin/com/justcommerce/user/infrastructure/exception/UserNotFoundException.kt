package com.justcommerce.user.infrastructure.exception

import com.justcommerce.common.exception.ResourceNotFoundException

class UserNotFoundException(message: String) : ResourceNotFoundException(message) {

    constructor(id: Long): this("사용자를 찾을 수 없습니다. [$id]")
}
