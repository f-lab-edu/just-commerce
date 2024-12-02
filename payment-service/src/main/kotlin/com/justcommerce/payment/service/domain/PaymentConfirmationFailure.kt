package com.justcommerce.payment.service.domain

data class PaymentConfirmationFailure (
    val errorCode: String,
    val message: String
)
