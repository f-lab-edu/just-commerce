package com.justcommerce.payment.infrastructure.exception

import com.justcommerce.common.exception.ResourceNotFoundException

class PaymentOrderEntityNotFoundException(orderId: String): ResourceNotFoundException("결제 상품을 찾을 수 없습니다. [$orderId]")
