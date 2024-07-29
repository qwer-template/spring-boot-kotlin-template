package com.qwer.api.reqs.base

import io.swagger.annotations.ApiModelProperty

/**
 * @Author: Kastrcn
 * @Date: 2023/9/19 19:04
 * @Version 1.0
 */
open class PageRequest {
    @ApiModelProperty(value = "当前页码", required = true)
    var pageIndex = 1

    @ApiModelProperty(value = "每页条数", required = true)
    var pageSize = 10
}
