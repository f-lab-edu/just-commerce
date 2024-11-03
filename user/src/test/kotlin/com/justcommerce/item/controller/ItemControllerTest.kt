package com.justcommerce.item.controller

import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest (
    private val mockMvc: MockMvc
): DescribeSpec ({

    describe("item을 단건 조회하는 API는") {

        context("item을 성공적으로 조회한다면") {

            val itemId = "C"
            it("ItemResponse를 반환한다") {
                mockMvc.perform(get("/items/$itemId"))
                    .andExpect(status().isOk)
                    .andDo(print())
                    .andExpect(jsonPath("$.id").value("C"))
                    .andExpect(jsonPath("$.title").value("toy"))
                    .andExpect(jsonPath("$.categories[0].id").value("220"))
                    .andExpect(jsonPath("$.categories[0].name").value("Toys & Hobbies"))
                    .andExpect(jsonPath("$.price.value").value("15.99"))
                    .andExpect(jsonPath("$.price.currency").value("KRW"))
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
