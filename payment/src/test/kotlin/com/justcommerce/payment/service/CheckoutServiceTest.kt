package com.justcommerce.payment.service

import com.justcommerce.payment.controller.port.CheckoutCommand
import com.justcommerce.payment.controller.port.CheckoutResult
import com.justcommerce.payment.mock.FindCartMockRepository
import com.justcommerce.payment.mock.FindUserMockRepository
import com.justcommerce.payment.mock.SavePaymentOrderMockRepository
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CheckoutServiceTest : DescribeSpec ({

    val checkoutService = CheckoutService(
        FindCartMockRepository(),
        FindUserMockRepository(),
        SavePaymentOrderMockRepository()
    )

    describe("체크아웃 생성 메서드는") {

        context("사용자 정보와 사용자의 카트가 존재한다면") {
            val userId = 1L
            val cartId = 1L
            val checkoutCommand = CheckoutCommand(userId, cartId)

            it("체크아웃 결과를 반환한다.") {
                val checkoutResultActual = checkoutService.create(checkoutCommand)

                val checkoutResultExpect = CheckoutResult(
                    orderId = "d334749d-6eb2-3321-a99b-1bed0a24a1f9",
                    orderName = "camera 외 2건",
                    amount = 280000,
                    customerName = "테스트1",
                    customerEmail = "서울",
                    customerMobilePhone = "01011111111"
                )
                checkoutResultActual shouldBe checkoutResultExpect
            }
        }
    }
})
