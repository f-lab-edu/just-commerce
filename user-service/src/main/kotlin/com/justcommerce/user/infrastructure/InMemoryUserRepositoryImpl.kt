package com.justcommerce.user.infrastructure

import com.justcommerce.common.domain.ClockHolder
import com.justcommerce.user.service.domain.User
import com.justcommerce.user.service.port.UserRepository
import org.springframework.stereotype.Repository
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@Repository
class InMemoryUserRepositoryImpl (
    clockHolder: ClockHolder
): UserRepository {

    private val registerTime = clockHolder.now()

    private val users = mapOf(
        1L to User(1L, "테스트1", "서울", "a@gmail.com", "01011112222", registerTime.minusDays(1)),
        2L to User(2L, "테스트2", "서울", "b@gmail.com", "01011112223", registerTime.minusDays(2)),
        3L to User(3L, "테스트3", "서울", "c@gmail.com", "01011112224", registerTime.minusDays(3))
    )

    override fun findById(id: Long): User? {
        return this.users[id]
    }

    override fun findAll(): List<User> {
        return users.values.toList()
    }
}
