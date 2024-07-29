package com.qwer.api.outner.controller

import com.alibaba.fastjson.JSONObject
import com.qwer.api.reqs.*
import com.qwer.api.resp.UserDetailResponse
import com.qwer.api.resp.UserInfoResponse
import com.qwer.api.resp.UserPageResponse
import com.qwer.api.resp.base.RespBody
import com.qwer.api.resp.base.RespPage
import com.qwer.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest

@RestController
@Api(description = "User管理")
class UserController {
    @Resource
    lateinit var httpServletRequest: HttpServletRequest;

    @Autowired
    lateinit var userService: UserService;

    companion object {
        private val logger = LoggerFactory.getLogger(UserController::class.java)

    }

    @ApiOperation("创建User版本")
    @PostMapping("api/user/create")
    fun create(@Validated @RequestBody request: UserCreateRequest): RespBody<Boolean> {
        val userId = httpServletRequest.getHeader("userId")
        logger.info("创建User版本 入参：userId {} ", JSONObject.toJSONString(request))
        return RespBody.success(userService.create(request, userId))
    }

    @ApiOperation("删除User版本")
    @PostMapping("api/user/delete")
    fun delete(@Validated @RequestBody request: UserDeleteRequest): RespBody<Boolean> {
        val userId = httpServletRequest.getHeader("userId")
        logger.info("删除User版本 入参：userId {} ", JSONObject.toJSONString(request))
        return RespBody.success(userService.delete(request, userId))
    }

    @ApiOperation("修改User版本")
    @PostMapping("api/user/update")
    fun update(@Validated @RequestBody request: UserUpdateRequest): RespBody<Boolean> {
        val userId = httpServletRequest.getHeader("userId")
        logger.info("修改User版本 入参：userId {} ", JSONObject.toJSONString(request))
        return RespBody.success(userService.update(request, userId))
    }

    @ApiOperation("User版本列表")
    @PostMapping("api/user/page")
    fun page(@Validated @RequestBody request: UserPageRequest): RespBody<RespPage<UserPageResponse>> {
        val userId = httpServletRequest.getHeader("userId")
        logger.info("User版本列表 入参：userId {} ", JSONObject.toJSONString(request))
        return RespBody.success(userService.page(request, userId))
    }


    @ApiOperation("User版本信息")
    @PostMapping("api/user/info")
    fun info(@Validated @RequestBody request: UserInfoRequest): RespBody<UserInfoResponse> {
        val userId = httpServletRequest.getHeader("userId")
        logger.info("User版本信息 入参：userId {} ", JSONObject.toJSONString(request))
        return RespBody.success(userService.info(request, userId))
    }

    @ApiOperation("User版本详情")
    @PostMapping("api/user/detail")
    fun detail(@Validated @RequestBody request: UserDetailRequest): RespBody<UserDetailResponse> {
        val userId = httpServletRequest.getHeader("userId")
        logger.info("User版本详情 入参：userId {} ", JSONObject.toJSONString(request))
        return RespBody.success(userService.detail(request, userId))
    }

}