package com.qwer.api.resp.base

import java.io.Serializable

class RespPage<T> : Serializable {
    /**
     * 列表
     */
    var list: List<T>

    /**
     * 总数
     */
    var total: Long

    /**
     * 总页数
     */
    var totalPages = 0

    constructor() {
        total = 0
        totalPages = 0
        list = ArrayList()
    }

    constructor(list: List<T>, total: Long) {
        this.list = list
        this.total = total
    }

    constructor(list: List<T>, total: Long, totalPages: Int) {
        this.list = list
        this.total = total
        this.totalPages = totalPages
    }
}
