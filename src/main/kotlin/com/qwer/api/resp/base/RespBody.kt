package com.qwer.api.resp.base

import com.qwer.util.ErrorCode
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*

@ApiModel
class RespBody<T> {
    @ApiModelProperty(value = "状态代码", example = "0")
    var errorCode: String? = null

    @ApiModelProperty(value = "状态信息", example = "成功")
    var value: String? = null

    @ApiModelProperty(value = "业务数据", example = "")
    var data: T? = null
        private set
    var systemCurrentTime = Date()

    constructor()
    constructor(errorCode: String?, value: String?) {
        this.errorCode = errorCode
        this.value = value
    }

    constructor(errorCode: String?, value: String?, data: T) {
        this.errorCode = errorCode
        this.value = value
        this.data = data
    }

    fun setData(data: T) {
        this.data = data
    }

    companion object {
        @JvmOverloads
        fun <T> success(errorMsg: ErrorCode = ErrorCode.OK, data: T? = null): RespBody<Any?> {
            return RespBody(errorMsg.value, errorMsg.memo, data)
        }

        fun <T> success(data: T): RespBody<T> {
            return RespBody(ErrorCode.OK.value, ErrorCode.OK.memo, data)
        }

        fun fail(errorMsg: ErrorCode): RespBody<Any?> {
            return RespBody(errorMsg.value, errorMsg.memo)
        }

        fun fail(errorCode: String?, value: String?): RespBody<Any?> {
            return RespBody(errorCode, value)
        }
    }
}
