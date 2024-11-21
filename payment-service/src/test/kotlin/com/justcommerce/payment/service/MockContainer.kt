package com.justcommerce.payment.service

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
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.mockito.kotlin.any

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
}
