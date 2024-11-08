package com.justcommerce.common.domain.exception

class CartNotFoundException(id: Int) : RuntimeException("카트를 찾을 수 없습니다. [$id]")
