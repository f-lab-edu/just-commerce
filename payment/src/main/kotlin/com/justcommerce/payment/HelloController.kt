package com.justcommerce.payment

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payments")
class HelloController {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello"
    }
}
