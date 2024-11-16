package com.justcommerce.user.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.justcommerce.common.domain.ClockHolder
import com.justcommerce.config.TestConfig
import com.justcommerce.user.controller.response.UserResponse
import com.justcommerce.utils.restdocs.restDocMockMvcBuild
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.restdocs.ManualRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@Import(TestConfig::class)
@AutoConfigureRestDocs
class UserControllerTest (
    private val clockHolder: ClockHolder,
    private val objectMapper: ObjectMapper,
    private val context: WebApplicationContext
): DescribeSpec ({

    val restDocumentation = ManualRestDocumentation()
    val mockMvc = restDocMockMvcBuild(context, restDocumentation)

    beforeEach { testcase -> restDocumentation.beforeTest(this.javaClass, testcase.name.testName) }
    afterEach { restDocumentation.afterTest() }

    describe("GET /users/{id}") {

        context("사용자를 성공적으로 조회한다면") {

            val id = 3
            it("UserResponse를 반환한다.") {
                val actualResult = mockMvc.perform(RestDocumentationRequestBuilders.get("/users/{id}", id))
                    .andExpect(MockMvcResultMatchers.status().isOk)
                    .andDo(document("find-user-by-id-success"))
                    .andReturn()

                val actual = objectMapper.readValue(actualResult.response.contentAsString, UserResponse::class.java)
                val expect = UserResponse (
                    id = 3L,
                    name = "테스트3",
                    createdAt = clockHolder.now().minusDays(3)
                )
                actual shouldBe expect
            }
        }

        context("사용자 조회에 실패한다면") {

            val id = "4"
            it("자원을 찾지 못 했다는 메시지와 함께 404 응답 코드를 반환한다.") {
                mockMvc.perform(RestDocumentationRequestBuilders.get("/users/{id}", id))
                    .andExpect(MockMvcResultMatchers.status().isNotFound)
                    .andExpect(MockMvcResultMatchers.content().string("사용자를 찾을 수 없습니다. [$id]"))
                    .andDo(document("find-user-by-id-fail"))
            }
        }
    }
})
