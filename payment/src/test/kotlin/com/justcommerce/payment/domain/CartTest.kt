package com.justcommerce.payment.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CartTest: FunSpec ({

    val cart = Cart(1,1,
        listOf(
            Item("1", "테스트1", 10, 2),
            Item("2", "테스트2", 20, 2),
            Item("3", "테스트3", 30, 2)
        )
    )

    test("카트의 이름을 추출한다.") {
        cart.extractCartName() shouldBe "테스트1 외 2건"
    }
})
