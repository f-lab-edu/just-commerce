package com.justcommerce.common.exception

import com.justcommerce.common.error.ErrorCode

open class ResourceNotFoundException(message: String): BusinessException(ErrorCode.ENTITY_NOT_FOUND, message)
