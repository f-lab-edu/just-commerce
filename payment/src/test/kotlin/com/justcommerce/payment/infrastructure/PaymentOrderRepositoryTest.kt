package com.justcommerce.payment.infrastructure

import com.justcommerce.payment.domain.PaymentOrder
import com.justcommerce.payment.domain.PaymentOrderItem
import com.justcommerce.payment.domain.PaymentStatus
import com.justcommerce.payment.infrastructure.jpa.PaymentOrderItemJpaRepository
import com.justcommerce.payment.infrastructure.jpa.PaymentOrderJpaRepository
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class PaymentOrderRepositoryTest (
    private val paymentOrderJpaRepository: PaymentOrderJpaRepository,
    private val paymentOrderItemJpaRepository: PaymentOrderItemJpaRepository
): DescribeSpec({

    val paymentOrderRepository = PaymentOrderRepository(
        paymentOrderJpaRepository,
        paymentOrderItemJpaRepository
    )
    extensions(SpringTestExtension(SpringTestLifecycleMode.Test))

    describe("save 메서드는") {

        context("주문 도메인의 정보를 이용하여") {
            val orderId = "d334749d-6eb2-3321-a99b-1bed0a24a1f9"
            val items = listOf(
                PaymentOrderItem("A", orderId, 10, 100000),
                PaymentOrderItem("B", orderId, 10, 150000),
                PaymentOrderItem("C", orderId, 10, 30000),
            )
            val paymentOrder = PaymentOrder (
                id = orderId,
                buyerId = 1,
                orderName = "camera 외 2건",
                paymentStatus = PaymentStatus.NOT_STARTED,
                items = items
            )
            it("주문 엔티티와 주문 아이템 엔티티를 생성하고 저장한다.") {
                val paymentOrderActual = paymentOrderRepository.save(paymentOrder)
                val paymentOrderExpect = PaymentOrder (
                    id = "d334749d-6eb2-3321-a99b-1bed0a24a1f9",
                    buyerId = 1,
                    orderName = "camera 외 2건",
                    paymentStatus = PaymentStatus.NOT_STARTED,
                    items= listOf(
                        PaymentOrderItem(itemId="A", orderId="d334749d-6eb2-3321-a99b-1bed0a24a1f9", sellerId=10, price=100000),
                        PaymentOrderItem(itemId="B", orderId="d334749d-6eb2-3321-a99b-1bed0a24a1f9", sellerId=10, price=150000),
                        PaymentOrderItem(itemId="C", orderId="d334749d-6eb2-3321-a99b-1bed0a24a1f9", sellerId=10, price=30000)
                    )
                )
                paymentOrderActual shouldBe paymentOrderExpect

                paymentOrderJpaRepository.findById(orderId).isPresent shouldBe true
                paymentOrderItemJpaRepository.findAllByOrderId(orderId).size shouldBe items.size
            }
        }
    }
})
