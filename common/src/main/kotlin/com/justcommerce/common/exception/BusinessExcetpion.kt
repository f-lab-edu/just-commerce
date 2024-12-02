package com.justcommerce.common.exception

import com.justcommerce.common.error.ErrorCode

open class BusinessException(
    val errorCode: ErrorCode,
    message: String
): RuntimeException(message) {
    constructor(errorCode: ErrorCode): this(errorCode, errorCode.message)
}
