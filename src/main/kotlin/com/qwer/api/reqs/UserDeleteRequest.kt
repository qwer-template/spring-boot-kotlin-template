package com.qwer.api.reqs

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

class UserDeleteRequest {

    @Schema(description = "id")
    @NotNull(message = "id不能为空")
    lateinit var id: String;

}