package com.justcommerce.payment.config

import feign.Logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfig {

    @Bean
    fun feignLogger() = Logger.Level.BASIC
}
