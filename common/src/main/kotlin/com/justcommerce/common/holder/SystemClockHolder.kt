package com.justcommerce.common.holder

import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class SystemClockHolder: ClockHolder {

    override fun now(): LocalDateTime {
        return LocalDateTime.now()
    }
}
