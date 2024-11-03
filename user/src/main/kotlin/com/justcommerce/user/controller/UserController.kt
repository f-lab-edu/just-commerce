package com.justcommerce.user.controller

import com.justcommerce.user.controller.port.UserReader
import com.justcommerce.user.controller.response.UserResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController (
    private val userReader: UserReader
) {

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): UserResponse {
        return UserResponse.from(userReader.getById(id))
    }
}
