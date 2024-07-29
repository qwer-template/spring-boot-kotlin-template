package com.qwer.util

import java.util.*

enum class ErrorCode(
    val value: String,
    val memo: String
) {
    OK("0", "成功"),
    NOT_FOUND("404", "资源不存在"),
    ERROR("500", "服务器错误"),
    PARAMETER_ERROR("501", "参数错误"),
    SYSTEM_ERROR("1002", "系统异常"),
    NOT_HAVE("502", "不存在"),
    ILLEGAL_ARGUMENT_ERROR("81030001", "非法参数"),
    EXIST_DATA("81030002", "已存在当天行业渠道数据");

    companion object {
        private val lookup: MutableMap<String, ErrorCode> = HashMap()

        init {
            for (errorCode in EnumSet.allOf(ErrorCode::class.java)) {
                lookup[errorCode.value] = errorCode
            }
        }

        fun get(value: String): ErrorCode? {
            return lookup[value]
        }
    }
}