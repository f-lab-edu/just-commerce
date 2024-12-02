package com.justcommerce.payment.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.justcommerce.common.error.ErrorCode
import com.justcommerce.common.error.ErrorResponse
import com.justcommerce.common.error.FieldError
import com.justcommerce.payment.controller.port.CheckoutCommand
import com.justcommerce.payment.controller.port.CheckoutResult
import com.justcommerce.payment.service.CheckoutService
import com.justcommerce.payment.utils.restdocs.restDocMockMvcBuild
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.ManualRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.context.WebApplicationContext

@WebMvcTest(CheckoutController::class)
@AutoConfigureRestDocs
class CheckoutControllerTest: DescribeSpec () {

    @MockBean
    private lateinit var checkoutService: CheckoutService

    @Autowired
    private lateinit var context: WebApplicationContext
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private lateinit var mockMvc: MockMvc
    private val restDocumentation = ManualRestDocumentation()

    init {
        beforeEach { testcase ->
            mockMvc = restDocMockMvcBuild(context, restDocumentation)
            restDocumentation.beforeTest(this.javaClass, testcase.name.testName)
        }
        afterEach { restDocumentation.afterTest() }

        describe("POST /checkout API는") {
            context("요청 바디에 담겨있는 사용자 ID와 카트ID가 존재한다면") {
                val checkoutCommand = CheckoutCommand (1L, 1L)
                val expect = CheckoutResult(
                    orderId = "d334749d-6eb2-3321-a99b-1bed0a24a1f9",
                    orderName = "camera 외 2건",
                    amount = 280000,
                    customerName = "테스트1",
                    customerEmail = "서울",
                    customerMobilePhone = "01011111111"
                )
                given(checkoutService.create(checkoutCommand)).willReturn(expect)

                it("CheckoutResult를 반환한다.") {
                    val actualResult = mockMvc.perform(RestDocumentationRequestBuilders.post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(checkoutCommand)))
                        .andExpect(MockMvcResultMatchers.status().isOk)
                        .andDo(MockMvcRestDocumentation.document("create-checkout-success"))
                        .andReturn()
                    val actual = objectMapper.readValue(actualResult.response.contentAsString, CheckoutResult::class.java)
                    actual shouldBe expect
                }
            }

            context("요청 바디에 사용자 ID 또는 카트 ID가 존재하지 않는다면") {
                val userId = "{\"userId\":1}"

                it("입력 유효성 검증 예외 정보를 반환한다") {
                    val actualResult = mockMvc.perform(RestDocumentationRequestBuilders.post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userId))
                        .andExpect(MockMvcResultMatchers.status().isBadRequest)
                        .andDo(MockMvcRestDocumentation.document("create-checkout-api-invalid-input-value"))
                        .andReturn()
                    val actual = objectMapper.readValue(actualResult.response.contentAsString, ErrorResponse::class.java)
                    val expect = ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, listOf(
                        FieldError(field="cartId", value="0", reason="1 이상의 값을 입력하세요.")
                    ))
                    actual shouldBe expect
                }
            }

            context("요청 바디의 사용자 ID 또는 카트 ID가 정수 타입이 아니라면") {
                val wrongTypeInput = "{\"userId\":sting,\"cartId\":string}"
                it("입력") {
                    val actualResult = mockMvc.perform(RestDocumentationRequestBuilders.post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(wrongTypeInput))
                        .andExpect(MockMvcResultMatchers.status().isBadRequest)
                        .andDo(MockMvcRestDocumentation.document("create-checkout-api-invalid-input-type"))
                        .andReturn()
                    val actual = objectMapper.readValue(actualResult.response.contentAsString, ErrorResponse::class.java)
                    val expect = ErrorResponse(ErrorCode.INVALID_INPUT_TYPE, "JSON parse error: Unrecognized token 'sting': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')")
                    actual shouldBe expect
                }
            }
        }
    }
}
