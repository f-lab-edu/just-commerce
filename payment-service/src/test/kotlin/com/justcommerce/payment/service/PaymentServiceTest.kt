package com.justcommerce.payment.service

import com.justcommerce.payment.controller.port.ConfirmationCommand
import com.justcommerce.payment.service.exception.PaymentConfirmationAmountNotMatchException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.throwable.shouldHaveMessage

class PaymentServiceTest: DescribeSpec({

    describe("결제 승인 요청 메서드는") {

        context("체크아웃 금액과 결제 승인 요청 금액이 서로 다르다면") {
            val confirmationCommand = ConfirmationCommand (
                "test paymentKey",
                "test orderId",
                10_000L
            )
            val validFailedPaymentService = MockContainer.validFailedPaymentService(confirmationCommand)

            it("금액이 서로 다르다는 예외를 반환한다.") {
                shouldThrow<PaymentConfirmationAmountNotMatchException> { validFailedPaymentService.confirm(confirmationCommand) }
                    .shouldHaveMessage("체크아웃 주문 금액과 결제 승인 요청 주문 금액이 다릅니다. [test orderId]")
            }
        }
    }

})
