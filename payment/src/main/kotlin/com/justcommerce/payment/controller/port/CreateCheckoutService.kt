package com.justcommerce.payment.controller.port

interface CreateCheckoutService {

    fun create(checkoutCommand: CheckoutCommand): CheckoutResult
}
