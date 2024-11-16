package com.justcommerce.config

import com.justcommerce.common.domain.ClockHolder
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime

@Configuration
class TestConfig {

    @Bean
    fun clockHolder(): ClockHolder = TestClockHolder(LocalDateTime.now())

    @Bean
    fun mockMvcEncodingCustomizer(): MockMvcBuilderCustomizer = MockMvcEncodingCustomizer()
}
