package com.justcommerce.payment.controller.port

import com.justcommerce.common.util.IdempotencyCreator

data class CheckoutCommand (
    val userId: Long,
    val cartId: Long
) {
    // 사용자 ID와 카트 ID를 기반으로 UUID를 생성하기에, 동일한 사용자가 동일한 카트에 대한 결제 요청을 여러 번 하더라도 한 건의 결제 요청으로 확인할 수 있다.
    val idempotencyKey: String = IdempotencyCreator.create(this)
}
