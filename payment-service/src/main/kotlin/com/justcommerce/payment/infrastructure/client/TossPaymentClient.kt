package com.justcommerce.payment.infrastructure.client

import com.justcommerce.payment.controller.port.ConfirmationCommand
import com.justcommerce.payment.infrastructure.client.response.TossPaymentResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(name = "pg-toss", url = "\${PG.toss.url}")
interface TossPaymentClient {

    @RequestMapping(
        value = ["\${PG.toss.confirm}"],
        method = [RequestMethod.POST]
    )
    fun confirm(
        @RequestBody body: ConfirmationCommand,
        @RequestHeader("Idempotency-Key") idempotencyKey: String,
        @RequestHeader(HttpHeaders.AUTHORIZATION) secretKey: String
    ): TossPaymentResponse
}
