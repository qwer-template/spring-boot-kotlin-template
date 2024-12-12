package com.qwer.api.outner.controller

import com.alibaba.fastjson.JSONObject
import com.qwer.api.reqs.*
import com.qwer.api.resp.UserDetailResponse
import com.qwer.api.resp.UserInfoResponse
import com.qwer.api.resp.UserPageResponse
import com.qwer.api.resp.base.RespBody
import com.qwer.api.resp.base.RespPage
import com.qwer.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "User管理")
class UserController {
    @Resource
    lateinit var httpServletRequest: HttpServletRequest;

    @Autowired
    lateinit var userService: UserService;

    companion object {
        private val logger = LoggerFactory.getLogger(UserController::class.java)

    }

    @Operation(summary = "创建User版本")
    @PostMapping("api/user/create")
    fun create(@Validated @RequestBody request: UserCreateRequest): RespBody<Boolean> {
        val userId = httpServletRequest.getHeader("userId")
        logger.info("创建User版本 入参：userId {} ", JSONObject.toJSONString(request))
        return RespBody.success(userService.create(request, userId))
    }

    @Operation(summary = "删除User版本")
    @PostMapping("api/user/delete")
    fun delete(@Validated @RequestBody request: UserDeleteRequest): RespBody<Boolean> {
        val userId = httpServletRequest.getHeader("userId")
        logger.info("删除User版本 入参：userId {} ", JSONObject.toJSONString(request))
        return RespBody.success(userService.delete(request, userId))
    }

    @Operation(summary = "修改User版本")
    @PostMapping("api/user/update")
    fun update(@Validated @RequestBody request: UserUpdateRequest): RespBody<Boolean> {
        val userId = httpServletRequest.getHeader("userId")
        logger.info("修改User版本 入参：userId {} ", JSONObject.toJSONString(request))
        return RespBody.success(userService.update(request, userId))
    }

    @Operation(summary = "User版本列表")
    @PostMapping("api/user/page")
    fun page(@Validated @RequestBody request: UserPageRequest): RespBody<RespPage<UserPageResponse>> {
        val userId = httpServletRequest.getHeader("userId")
        logger.info("User版本列表 入参：userId {} ", JSONObject.toJSONString(request))
        return RespBody.success(userService.page(request, userId))
    }


    @Operation(summary = "User版本信息")
    @PostMapping("api/user/info")
    fun info(@Validated @RequestBody request: UserInfoRequest): RespBody<UserInfoResponse> {
        val userId = httpServletRequest.getHeader("userId")
        logger.info("User版本信息 入参：userId {} ", JSONObject.toJSONString(request))
        return RespBody.success(userService.info(request, userId))
    }

    @Operation(summary = "User版本详情")
    @PostMapping("api/user/detail")
    fun detail(@Validated @RequestBody request: UserDetailRequest): RespBody<UserDetailResponse> {
        val userId = httpServletRequest.getHeader("userId")
        logger.info("User版本详情 入参：userId {} ", JSONObject.toJSONString(request))
        return RespBody.success(userService.detail(request, userId))
    }

}