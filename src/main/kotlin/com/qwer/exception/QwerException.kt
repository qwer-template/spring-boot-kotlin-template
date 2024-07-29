package com.qwer.exception

import com.qwer.util.ErrorCode

class QwerException : RuntimeException {
    private var errorCode: ErrorCode? = null

    constructor(msg: String?) : super(msg)
    constructor(msg: String?, e: Throwable?) : super(msg, e)
    constructor(errorCode: ErrorCode) : super(errorCode.memo) {
        this.errorCode = errorCode
    }

    fun getErrorCode(): ErrorCode? {
        return errorCode
    }

    fun setErrorCode(errorCode: ErrorCode?) {
        this.errorCode = errorCode
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
