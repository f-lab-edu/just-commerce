package com.justcommerce.common.domain.exception

class ItemNotFoundException(id: String) : RuntimeException("상품을 찾을 수 없습니다. [$id]")
