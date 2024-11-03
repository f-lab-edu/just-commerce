package com.justcommerce.user.infrastructure

import com.justcommerce.user.domain.User
import com.justcommerce.user.service.port.UserRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class InMemoryUserRepositoryImpl: UserRepository {

    private val users = mapOf(
        1L to User(1L, "테스트1", "서울", LocalDateTime.now().minusDays(2)),
        2L to User(2L, "테스트2", "서울", LocalDateTime.now().minusDays(1)),
        3L to User(3L, "테스트3", "서울", LocalDateTime.now())
    )

    override fun findById(id: Long): User? {
        return this.users[id]
    }
}
