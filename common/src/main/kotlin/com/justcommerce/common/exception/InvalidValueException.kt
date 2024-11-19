package com.justcommerce.common.exception

import com.justcommerce.common.error.ErrorCode

open class InvalidValueException(
    errorCode: ErrorCode,
    value: String
): BusinessException(errorCode, value) {

    constructor(value: String): this(ErrorCode.INVALID_INPUT_VALUE, value)
}
