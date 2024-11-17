package com.justcommerce

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class ItemApplication

fun main(args: Array<String>) {
    runApplication<ItemApplication>(*args)
}
