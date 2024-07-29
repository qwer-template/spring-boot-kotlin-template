package com.qwer.api.reqs

import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotNull

class UserDeleteRequest {

    @ApiModelProperty(value = "id")
    @NotNull(message = "id不能为空")
    lateinit var id: String;

}