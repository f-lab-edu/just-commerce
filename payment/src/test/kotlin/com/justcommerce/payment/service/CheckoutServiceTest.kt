package com.justcommerce.payment.service

import com.justcommerce.payment.DependencyConfig
import com.justcommerce.payment.controller.port.CheckoutCommand
import com.justcommerce.payment.controller.port.CheckoutResult
import com.justcommerce.payment.domain.PaymentStatus
import com.justcommerce.payment.infrastructure.PaymentOrderItemJpaRepository
import com.justcommerce.payment.infrastructure.PaymentOrderJpaRepository
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@Import(DependencyConfig::class)
class CheckoutServiceTest (
    private val checkoutService: CheckoutService,
    private val paymentOrderJpaRepository: PaymentOrderJpaRepository,
    private val paymentOrderItemJpaRepository: PaymentOrderItemJpaRepository
): DescribeSpec ({

    extensions(SpringTestExtension(SpringTestLifecycleMode.Test))

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

            it("주문 정보와 주문 아이템 정보를 저장한다.") {
                val checkoutResult = checkoutService.create(checkoutCommand)

                paymentOrderJpaRepository.getReferenceById(checkoutResult.orderId).apply {
                    this.id shouldBe "d334749d-6eb2-3321-a99b-1bed0a24a1f9"
                    this.buyerId shouldBe 1L
                    this.orderName shouldBe "camera 외 2건"
                    this.paymentStatus shouldBe PaymentStatus.NOT_STARTED
                }

                paymentOrderItemJpaRepository.findAllByOrderId(checkoutResult.orderId).size shouldBe 3
            }
        }
    }
})
