package com.justcommerce.payment.infrastructure.webclient

import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import java.lang.Exception
import java.lang.RuntimeException

@Component
class FeignErrorDecoder: ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception {
        return when (response.status()) {
            400, 404 -> ResponseStatusException(HttpStatus.valueOf(response.status()))
            else -> RuntimeException("FeignErrorDecoder exception")
        }
    }
}
