package com.justcommerce.common.domain

import java.time.LocalDateTime

interface ClockHolder {

    fun now(): LocalDateTime
}
