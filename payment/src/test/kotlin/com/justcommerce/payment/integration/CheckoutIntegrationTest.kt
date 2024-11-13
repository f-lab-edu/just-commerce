package com.justcommerce.payment.integration

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.transaction.annotation.Transactional
import java.net.URI

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class CheckoutIntegrationTest {

    @Autowired
    private lateinit var testRestTemplate: TestRestTemplate
    private val userId = 1
    private val cartId = 1

    @Test
    @DisplayName("GET /checkout/{userId}/{cartId}")
    fun checkout() {
        val requestEntity = RequestEntity(null, HttpMethod.GET, URI("/checkout/$userId/$cartId"))
        val responseEntity = testRestTemplate.exchange<Any>(requestEntity)
        // TODO 체크아웃 개발 완료
    }
}
