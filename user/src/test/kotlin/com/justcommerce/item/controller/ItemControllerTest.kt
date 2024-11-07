package com.justcommerce.item.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.justcommerce.item.controller.response.ItemResponse
import com.justcommerce.item.domain.Category
import com.justcommerce.item.domain.Price
import com.justcommerce.utils.restdocs.restDocMockMvcBuild
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.restdocs.ManualRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@AutoConfigureRestDocs
class ItemControllerTest (
    private val objectMapper: ObjectMapper,
    private val context: WebApplicationContext
): DescribeSpec ({

    val restDocumentation = ManualRestDocumentation()
    val mockMvc = restDocMockMvcBuild(context, restDocumentation)

    beforeEach { testcase -> restDocumentation.beforeTest(this.javaClass, testcase.name.testName) }
    afterEach { restDocumentation.afterTest() }

    describe("item을 단건 조회하는 API는") {

        context("item을 성공적으로 조회한다면") {

            val itemId = "C"
            val expect = ItemResponse (
                id = "C",
                title = "toy",
                categories = listOf(Category("220", "Toys & Hobbies")),
                price = Price("150000", "KRW"),
                sellerId = 3
            )
            it("ItemResponse를 반환한다") {
                val actualResult = mockMvc.perform(RestDocumentationRequestBuilders.get("/items/{id}", itemId))
                    .andExpect(status().isOk)
                    .andDo(document("find-item-by-id-success"))
                    .andReturn()

                val actual = objectMapper.readValue(actualResult.response.contentAsString, ItemResponse::class.java)
                actual shouldBe expect
            }
        }

        context("item 조회에 실패한다면") {

            val itemId = "wrongId"
            it("자원을 찾지 못 했다는 메시지와 함께 404 응답 코드를 반환한다.") {
                mockMvc.perform(RestDocumentationRequestBuilders.get("/items/{id}", itemId))
                    .andExpect(status().isNotFound)
                    .andExpect(content().string("상품을 찾을 수 없습니다. [$itemId]"))
                    .andDo(document("find-item-by-id-fail"))
            }
        }
    }
})
