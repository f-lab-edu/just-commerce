package com.justcommerce.payment.infrastructure.client

import com.justcommerce.payment.service.domain.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "user-service")
interface UserServiceClient {

    @GetMapping("/users/{userId}")
    fun getUser(@PathVariable userId: Long): User
}
