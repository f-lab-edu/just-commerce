package com.justcommerce.payment.controller.request

import jakarta.validation.constraints.Min

data class CreateCheckoutRequest (
    @field:Min(value = 1, message = "1 이상의 값을 입력하세요.") val userId: Long,
    @field:Min(value = 1, message = "1 이상의 값을 입력하세요.") val cartId: Long
)
