package com.justcommerce.payment.infrastructure.client.response

data class TossPaymentResponse (
    val paymentKey: String,
    val type: String,
    val orderId: String,
    val orderName: String,
    val mId: String,
    val currency: String,
    val method: String?,
    val totalAmount: Double,
    val balanceAmount: Double,
    val status: String,
    val requestedAt: String,
    val approvedAt: String?,
    val useEscrow: Boolean,
    val lastTransactionKey: String?,
    val suppliedAmount: Double,
    val vat: Double,
    val cancels: List<Cancel>? = null,
    val isPartialCancelable: Boolean,
    val card: CardInfo?,
    val mobilePhone: MobilePhoneInfo?,
    val receipt: Receipt?,
    val checkout: Checkout?,
    val easyPay: EasyPay?,
    val country: String,
    val failure: Failure?
) {
    data class Cancel(
        val cancelAmount: Double,
        val cancelReason: String,
        val taxFreeAmount: Double,
        val taxExemptionAmount: Int,
        val refundableAmount: Double,
        val easyPayDiscountAmount: Double,
        val canceledAt: String,
        val transactionKey: String,
        val receiptKey: String?,
        val cancelStatus: String,
        val cancelRequestId: String?
    )

    data class CardInfo(
        val amount: Double,
        val issuerCode: String,
        val acquirerCode: String?,
        val number: String,
        val installmentPlanMonths: Int,
        val approveNo: String,
        val useCardPoint: Boolean,
        val cardType: String,
        val ownerType: String,
        val acquireStatus: String,
        val isInterestFree: Boolean,
        val interestPayer: String?
    )

    data class MobilePhoneInfo(
        val customerMobilePhone: String,
        val settlementStatus: String,
        val receiptUrl: String
    )

    data class Receipt(
        val url: String
    )

    data class Checkout(
        val url: String
    )

    data class EasyPay(
        val provider: String,
        val amount: Double,
        val discountAmount: Double
    )

    data class Failure(
        val code: String,
        val message: String
    )
}
