package com.justcommerce.item.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.justcommerce.item.controller.response.ItemResponse
import com.justcommerce.item.domain.Category
import com.justcommerce.item.domain.Price
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest (
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
): DescribeSpec ({

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
                val actualResult = mockMvc.perform(get("/items/$itemId"))
                    .andExpect(status().isOk)
                    .andReturn()

                val actual = objectMapper.readValue(actualResult.response.contentAsString, ItemResponse::class.java)
                actual shouldBe expect
            }
        }

        context("item 조회에 실패한다면") {

            val itemId = "error id"
            it("자원을 찾지 못 했다는 메시지와 함께 404 응답 코드를 반환한다.") {
                mockMvc.perform(get("/items/$itemId"))
                    .andExpect(status().isNotFound)
                    .andExpect(content().string("상품을 찾을 수 없습니다. [$itemId]"))
            }
        }
    }
})
