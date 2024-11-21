package com.justcommerce.payment.controller.port

import com.justcommerce.payment.service.domain.PaymentConfirmationFailure
import com.justcommerce.payment.service.domain.PaymentStatus
import com.justcommerce.payment.service.exception.PaymentConfirmationFailureNotExistException

data class ConfirmationResult (
    val status: PaymentStatus,
    val traceId: String,
    val failure: PaymentConfirmationFailure?
) {
    init {
        if (status == PaymentStatus.FAILURE && failure == null) {
            throw PaymentConfirmationFailureNotExistException("결제에 실패하였다면 결제 실패 사유는 항상 존재하여야 합니다.")
        }
    }

    val message = when (status) {
        PaymentStatus.SUCCESS -> "결제 처리에 성공하였습니다."
        PaymentStatus.FAILURE -> "결제 처리에 실패하였습니다."
        PaymentStatus.UNKNOWN -> "결제 처리 중에 알 수 없는 에러가 발생하였습니다."
        else -> error("현재 결제 상태 (status: $status) 는 올바르지 않은 상태입니다. ")
    }
}
