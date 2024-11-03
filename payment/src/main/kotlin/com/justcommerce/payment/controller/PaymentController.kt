package com.justcommerce.payment.controller

import com.justcommerce.payment.controller.request.TossPaymentConfirmRequest
import com.justcommerce.payment.controller.response.TossPaymentConfirmResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v2/toss")
class PaymentController {

    @PostMapping("/confirm/widget")
    fun confirm(@RequestBody request: TossPaymentConfirmRequest): TossPaymentConfirmResponse {
        // TODO
        // 1. 결제 승인 데이터 검증
        // 2. 결제 상태 업데이트
        // 3. 결제 승인 https://api.tosspayments.com/v1/payments/confirm 전송
        // 4. 결제 승인 실패 시 결제 승인 재요청 전략 수립 필요
        // 5. 결제 상태 업데이트 (성공이라면 장부와 판매자 지갑에도 업데이트 필요)

        return TossPaymentConfirmResponse("OK", "")
    }
}
