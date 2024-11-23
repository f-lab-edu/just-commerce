package com.justcommerce.config

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringExtension

class KotestConfig: AbstractProjectConfig() {

    override fun extensions() = listOf(SpringExtension)

}
