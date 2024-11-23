package com.justcommerce.common.holder

import java.time.LocalDateTime

interface ClockHolder {

    fun now(): LocalDateTime
}
