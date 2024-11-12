package com.justcommerce.payment

import com.justcommerce.payment.infrastructure.PaymentOrderItemJpaRepository
import com.justcommerce.payment.infrastructure.PaymentOrderJpaRepository
import com.justcommerce.payment.infrastructure.PaymentOrderRepository
import com.justcommerce.payment.mock.FindCartMockRepository
import com.justcommerce.payment.mock.FindUserMockRepository
import com.justcommerce.payment.service.CheckoutService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyConfig {

    @Bean
    fun checkoutService(
        paymentOrderJpaRepository: PaymentOrderJpaRepository,
        paymentOrderItemJpaRepository: PaymentOrderItemJpaRepository
    ): CheckoutService {
        return CheckoutService(
            FindCartMockRepository(),
            FindUserMockRepository(),
            PaymentOrderRepository(
                paymentOrderJpaRepository,
                paymentOrderItemJpaRepository
            )
        )
    }
}
