package com.justcommerce.common.controller

import com.justcommerce.common.domain.exception.CartNotFoundException
import com.justcommerce.common.domain.exception.CartOwnerMismatchException
import com.justcommerce.common.domain.exception.ItemNotFoundException
import com.justcommerce.common.domain.exception.UserNotFoundException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ExceptionControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException::class, ItemNotFoundException::class, CartNotFoundException::class, CartOwnerMismatchException::class)
    fun resourceNotFoundException(e: RuntimeException) = e.message
}
