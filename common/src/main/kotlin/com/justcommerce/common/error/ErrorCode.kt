package com.justcommerce.common.error

enum class ErrorCode (
    val status: Int,
    val code: String,
    val message: String
) {
    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", "Invalid Input Value"),
    ENTITY_NOT_FOUND(404, "C003", "Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_INPUT_TYPE(400, "C005", "Invalid Input Type"),
}
