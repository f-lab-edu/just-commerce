package com.justcommerce.common.infrastructure

import com.justcommerce.common.domain.ClockHolder
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class SystemClockHolder: ClockHolder {

    override fun now(): LocalDateTime {
        return LocalDateTime.now()
    }
}
