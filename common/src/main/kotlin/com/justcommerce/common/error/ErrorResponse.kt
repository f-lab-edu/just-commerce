package com.justcommerce.common.error

import org.springframework.validation.BindingResult

data class ErrorResponse (
    val message: String,
    val errors: List<FieldError>,
    val code: String
) {
    constructor(code: ErrorCode): this(code.message, emptyList(), code.code)
    constructor(code: ErrorCode, message: String): this(message, emptyList(), code.code)
    constructor(code: ErrorCode, errors: List<FieldError>): this(code.message, errors, code.code)

    companion object {
        fun of(code: ErrorCode, bindingResult: BindingResult): ErrorResponse {
            return ErrorResponse(code, FieldError.of(bindingResult))
        }

        fun of(code: ErrorCode, message: String): ErrorResponse {
            return ErrorResponse(code, message)
        }

        fun of(code: ErrorCode): ErrorResponse {
            return ErrorResponse(code)
        }
    }
}
