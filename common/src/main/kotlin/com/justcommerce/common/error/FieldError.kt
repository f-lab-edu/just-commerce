package com.justcommerce.common.error

import org.springframework.validation.BindingResult

data class FieldError (
    val field: String,
    val value: String,
    val reason: String
) {
    companion object {
        fun of(bindingResult: BindingResult): List<FieldError> {
            return bindingResult.fieldErrors
                .map {
                    FieldError(
                        it.field,
                        it.rejectedValue?.toString() ?: "",
                        it.defaultMessage ?: ""
                    )
                }
        }
    }
}
