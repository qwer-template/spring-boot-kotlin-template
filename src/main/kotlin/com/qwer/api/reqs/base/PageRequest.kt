package com.qwer.api.reqs.base

import io.swagger.v3.oas.annotations.media.Schema


/**
 * @Author: Kastrcn
 * @Date: 2023/9/19 19:04
 * @Version 1.0
 */
open class PageRequest {
    @Schema(description = "当前页码", required = false, defaultValue = "1")
    var pageIndex = 1

    @Schema(description = "每页条数", required = false, defaultValue = "10")
    var pageSize = 10
}
