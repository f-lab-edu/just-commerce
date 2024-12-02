package com.justcommerce.config

import com.justcommerce.common.holder.ClockHolder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime

@Configuration
class TestConfig {

    @Bean
    fun clockHolder(): ClockHolder = TestClockHolder(LocalDateTime.now())
}
