package com.justcommerce.wallet.service.domain

enum class TransactionType (val describe: String) {
    DEBIT("차변"), CREDIT("대변")
}
