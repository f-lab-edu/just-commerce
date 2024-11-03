package com.justcommerce.wallet.controller.request

import com.justcommerce.payment.domain.TransactionType

data class WalletTransactionCreateRequest (
    val userId: Long,
    val amount: Long,
    val type: TransactionType
)