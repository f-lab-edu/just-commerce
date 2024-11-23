package com.justcommerce.wallet.controller

import com.justcommerce.wallet.controller.request.WalletTransactionCreateRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/wallets")
class WalletController {

    @PostMapping("/{userId}")
    fun createTransaction(@RequestBody request: WalletTransactionCreateRequest) {
        // TODO 판매자의 지갑에 판매 금액을 저장한다. (Wallet 1 : Wallet Transactions N)
    }
}
