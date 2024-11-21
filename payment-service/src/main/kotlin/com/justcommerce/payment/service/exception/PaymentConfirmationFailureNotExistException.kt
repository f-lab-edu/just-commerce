package com.justcommerce.payment.service.exception

import com.justcommerce.common.error.ErrorCode
import com.justcommerce.common.exception.BusinessException

class PaymentConfirmationFailureNotExistException(msg: String): BusinessException(ErrorCode.INTERNAL_SERVER_ERROR, msg)
