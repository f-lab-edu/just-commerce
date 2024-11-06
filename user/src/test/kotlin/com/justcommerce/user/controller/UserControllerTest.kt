package com.justcommerce.user.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.justcommerce.common.domain.ClockHolder
import com.justcommerce.config.TestConfig
import com.justcommerce.user.controller.response.UserResponse
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@Import(TestConfig::class)
@AutoConfigureMockMvc
class UserControllerTest (
    private val mockMvc: MockMvc,
    private val clockHolder: ClockHolder,
    private val objectMapper: ObjectMapper
): DescribeSpec ({

    describe("단일 사용자를 조회하는 API는") {

        context("사용자를 성공적으로 조회한다면") {

            val userId = 3
            val expect = UserResponse (
                id = 3L,
                name = "테스트3",
                createdAt = clockHolder.now().minusDays(3)
            )
            it("UserResponse를 반환한다.") {
                val actualResult = mockMvc.perform(MockMvcRequestBuilders.get("/users/$userId"))
                    .andExpect(MockMvcResultMatchers.status().isOk)
                    .andReturn()

                val actual = objectMapper.readValue(actualResult.response.contentAsString, UserResponse::class.java)
                actual shouldBe expect
            }
        }

        context("사용자 조회에 실패한다면") {

            val userId = "4"
            it("자원을 찾지 못 했다는 메시지와 함께 404 응답 코드를 반환한다.") {
                mockMvc.perform(MockMvcRequestBuilders.get("/users/$userId"))
                    .andExpect(MockMvcResultMatchers.status().isNotFound)
                    .andExpect(MockMvcResultMatchers.content().string("사용자를 찾을 수 없습니다. [$userId]"))
            }
        }
    }

})
