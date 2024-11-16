package com.justcommerce.payment.integration

import com.justcommerce.payment.controller.port.CheckoutResult
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatusCode
import org.springframework.http.RequestEntity
import org.springframework.transaction.annotation.Transactional
import java.net.URI
import kotlin.test.fail

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class CheckoutIntegrationTest {

    @Autowired
    private lateinit var testRestTemplate: TestRestTemplate

    @Test
    @DisplayName("POST /checkout")
    fun checkout() {
        val body = mapOf(
            "userId" to 1,
            "cartId" to 1
        )

        val requestEntity = RequestEntity(body, HttpMethod.POST, URI("/checkout"))
        val responseEntity = testRestTemplate.exchange<CheckoutResult>(requestEntity)

        responseEntity.statusCode.is2xxSuccessful shouldBe true
        val actual = responseEntity.body ?: fail("정상의 경우 체크아웃 응답은 항상 존재합니다.")
        val expect = CheckoutResult (
            orderId = "d334749d-6eb2-3321-a99b-1bed0a24a1f9",
            orderName = "camera 외 2건",
            amount = 280000,
            customerName = "테스트1",
            customerEmail = "a@gmail.com",
            customerMobilePhone = "01011112222"
        )
        actual shouldBe expect
    }
}
