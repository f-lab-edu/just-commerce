package com.justcommerce.item.infrastructure.exception

import com.justcommerce.common.exception.ResourceNotFoundException

class CartNotFoundException(id: Long) : ResourceNotFoundException("카트를 찾을 수 없습니다. [$id]")
