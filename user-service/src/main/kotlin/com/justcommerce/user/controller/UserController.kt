package com.justcommerce.user.controller

import com.justcommerce.user.controller.port.FindUserService
import com.justcommerce.user.controller.response.UserResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController (
    private val findUserService: FindUserService
) {

    @GetMapping
    fun findAll(): List<UserResponse> {
        return findUserService.findAll().map { UserResponse.from(it) }
    }

    @GetMapping("/{id}")
    fun findUserById(@PathVariable id: Long): UserResponse {
        return UserResponse.from(findUserService.findById(id))
    }
}
