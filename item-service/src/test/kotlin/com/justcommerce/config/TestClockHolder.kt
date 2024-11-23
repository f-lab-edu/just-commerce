package com.justcommerce.config

import com.justcommerce.common.holder.ClockHolder
import java.time.LocalDateTime

class TestClockHolder (
    private val clockHolder: LocalDateTime
): ClockHolder {

    override fun now(): LocalDateTime {
        return clockHolder
    }
}
