package com.justcommerce.payment.service

import com.justcommerce.payment.controller.port.ConfirmationCommand
import com.justcommerce.payment.infrastructure.CartRepository
import com.justcommerce.payment.infrastructure.UserRepository
import com.justcommerce.payment.infrastructure.client.CartServiceClient
import com.justcommerce.payment.infrastructure.client.UserServiceClient
import com.justcommerce.payment.service.domain.Cart
import com.justcommerce.payment.service.domain.Item
import com.justcommerce.payment.service.domain.PaymentOrder
import com.justcommerce.payment.service.domain.PaymentStatus
import com.justcommerce.payment.service.domain.User
import com.justcommerce.payment.service.port.CommandPaymentOrderRepository
import com.justcommerce.payment.service.port.ExecutePaymentOrderRepository
import com.justcommerce.payment.service.port.FindPaymentOrderRepository
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.anyVararg
import org.mockito.kotlin.given

object MockContainer {

    fun checkoutService(userId: Long, cartId: Long): CheckoutService {
        val userServiceMockClient = Mockito.mock(UserServiceClient::class.java).apply {
            BDDMockito.given(this.getUser(userId)).willReturn(User(1L, "테스트1", "서울", "01011111111"))
        }
        val cartServiceMockClient = Mockito.mock(CartServiceClient::class.java).apply {
            val items = listOf(
                Item("A", "camera", 30000, 10),
                Item("B", "rcVehicle", 100000, 10),
                Item("C", "toy", 150000, 3)
            )
            BDDMockito.given(this.getCartByUserIdAndCartId(userId, cartId)).willReturn(Cart (userId, cartId, items))
        }
        val savePaymentOrderMockRepository = Mockito.mock(CommandPaymentOrderRepository::class.java).apply {
            BDDMockito.given(this.save(any<PaymentOrder>())).willAnswer { invocation ->
                val order = invocation.getArgument<PaymentOrder>(0)
                PaymentOrder (
                    id = order.id,
                    buyerId = order.buyerId,
                    orderName = order.orderName,
                    paymentStatus = PaymentStatus.NOT_STARTED,
                    items = order.items
                )
            }
        }
        return CheckoutService(
            CartRepository(cartServiceMockClient),
            UserRepository(userServiceMockClient),
            savePaymentOrderMockRepository
        )
    }

    fun validFailedPaymentService(command: ConfirmationCommand): PaymentService {
        val findPaymentOrderRepository = Mockito.mock(FindPaymentOrderRepository::class.java).apply {
            val actualReturn = PaymentOrder(
                command.orderId,
                1L,
                "결제 금액이 서로 다른 케이스",
                PaymentStatus.NOT_STARTED,
                emptyList()
            )
            BDDMockito.given(this.findPaymentOrder(command.orderId)).willReturn(actualReturn)
        }
        val commandPaymentOrderRepository = Mockito.mock(CommandPaymentOrderRepository::class.java).apply {
            BDDMockito.given(this.statusUpdate(any<String>(), any<PaymentStatus>())).willThrow(RuntimeException("실행 되어서는 안됩니다."))
        }
        val executePaymentOrderRepository = Mockito.mock(ExecutePaymentOrderRepository::class.java).apply {
            BDDMockito.given(this.execute(any<ConfirmationCommand>())).willThrow(RuntimeException("실행 되어서는 안됩니다."))
        }
        return PaymentService (
            findPaymentOrderRepository,
            commandPaymentOrderRepository,
            executePaymentOrderRepository
        )
    }
}
