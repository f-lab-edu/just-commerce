package com.justcommerce.item.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.justcommerce.common.error.ErrorResponse
import com.justcommerce.item.controller.port.FindItemService
import com.justcommerce.item.controller.response.ItemResponse
import com.justcommerce.item.infrastructure.exception.ItemNotFoundException
import com.justcommerce.item.service.domain.Category
import com.justcommerce.item.service.domain.Item
import com.justcommerce.utils.restdocs.restDocMockMvcBuild
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.restdocs.ManualRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.context.WebApplicationContext
import java.time.LocalDateTime

@WebMvcTest(ItemController::class)
@AutoConfigureRestDocs
class ItemControllerTest: DescribeSpec () {

    @MockBean
    private lateinit var findItemService: FindItemService

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

        describe("GET /items/{id} API는") {

            context("item을 성공적으로 조회한다면") {

                val itemId = "C"
                given(findItemService.findById(itemId)).willReturn(Item(
                    id = "C",
                    title = "toy",
                    categories = listOf(Category("220", "Toys & Hobbies")),
                    price = 30000,
                    sellerId = 3,
                    LocalDateTime.now()
                ))
                it("ItemResponse를 반환한다") {
                    val actualResult = mockMvc.perform(RestDocumentationRequestBuilders.get("/items/{id}", itemId))
                        .andExpect(status().isOk)
                        .andDo(document("find-item-by-id"))
                        .andReturn()

                    val actual = objectMapper.readValue(actualResult.response.contentAsString, ItemResponse::class.java)
                    val expect = ItemResponse (
                        id = "C",
                        title = "toy",
                        categories = listOf(Category("220", "Toys & Hobbies")),
                        price = 30000,
                        sellerId = 3
                    )
                    actual shouldBe expect
                }
            }

            context("item 조회에 실패한다면") {

                val itemId = "wrongId"
                given(findItemService.findById(itemId)).willThrow(ItemNotFoundException(itemId))

                it("자원을 찾지 못 했다는 메시지와 함께 404 응답 코드를 반환한다.") {
                    val actualResult = mockMvc.perform(RestDocumentationRequestBuilders.get("/items/{id}", itemId))
                        .andExpect(status().isNotFound)
                        .andDo(document("item-not-found"))
                        .andReturn()

                    val actual = objectMapper.readValue(actualResult.response.contentAsString, ErrorResponse::class.java)
                    val expect = ErrorResponse (
                        message = "상품을 찾을 수 없습니다. [$itemId]",
                        errors = emptyList(),
                        code = "C003"
                    )
                    actual shouldBe expect
                }
            }
        }
    }
}
