package com.justcommerce.config

import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder

class MockMvcEncodingCustomizer : MockMvcBuilderCustomizer {
    override fun customize(builder: ConfigurableMockMvcBuilder<*>?) {
        builder?.alwaysDo { it.response.characterEncoding = "UTF-8" }
    }
}
