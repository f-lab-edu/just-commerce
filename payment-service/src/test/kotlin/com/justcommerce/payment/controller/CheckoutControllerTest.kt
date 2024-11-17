package com.justcommerce.payment.controller

import com.justcommerce.payment.controller.port.CheckoutCommand
import com.justcommerce.payment.controller.port.CheckoutResult
import com.justcommerce.payment.service.CheckoutService
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import kotlin.test.fail

@WebMvcTest(CheckoutController::class)
class CheckoutControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc
    @MockBean
    private lateinit var checkoutService: CheckoutService
    private val userId = 1L
    private val cartId = 1L

    @Test
    @DisplayName("GET /checkout/{userId}/{cartId}")
    fun checkout() {
        val expect = CheckoutResult(
            orderId = "d334749d-6eb2-3321-a99b-1bed0a24a1f9",
            orderName = "camera 외 2건",
            amount = 280000,
            customerName = "테스트1",
            customerEmail = "서울",
            customerMobilePhone = "01011111111"
        )
        `when`(checkoutService.create(CheckoutCommand(userId, cartId))).thenReturn(expect)

        val actualResult = mockMvc.perform(MockMvcRequestBuilders.get("/checkout/$userId/$cartId"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        val model = actualResult.modelAndView?.model ?: fail()
        model["orderId"] shouldBe expect.orderId
        model["orderName"] shouldBe expect.orderName
        model["amount"] shouldBe expect.amount
        model["customerName"] shouldBe expect.customerName
        model["customerEmail"] shouldBe expect.customerEmail
    }
}
