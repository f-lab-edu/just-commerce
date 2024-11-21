package com.justcommerce.payment.infrastructure.exception

import com.justcommerce.common.exception.ResourceNotFoundException

class PaymentOrderNotFoundException(orderId: String): ResourceNotFoundException("결제 정보를 찾을 수 없습니다. [$orderId]")
