package com.justcommerce.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/{domain}")
class HelloController {

    @GetMapping("/hello")
    fun hello(@PathVariable domain: String): String {
        return "Hello $domain"
    }
}
