package com.justcommerce.item.infrastructure.exception

import com.justcommerce.common.exception.ResourceNotFoundException

class ItemNotFoundException(id: String) : ResourceNotFoundException("상품을 찾을 수 없습니다. [$id]")
