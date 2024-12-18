package com.qwer.service

import com.qwer.api.reqs.*
import com.qwer.api.resp.UserDetailResponse
import com.qwer.api.resp.UserInfoResponse
import com.qwer.api.resp.UserPageResponse
import com.qwer.api.resp.base.RespPage
import com.qwer.domain.User
import com.qwer.exception.QwerException
import com.qwer.repository.UserRepository
import com.qwer.util.ErrorCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(val userRepository: UserRepository) {


    fun create(request: UserCreateRequest, userId: String): Boolean {
        val user = User(
            username = request.username,
            password = request.password,
            createTime = Date(),
            updateTime = Date(),
            deleted = false,
        )
        userRepository.save(user)
        return true
    }

    fun delete(request: UserDeleteRequest, userId: String): Boolean {
        val queryUser = User(
            id = request.id,
            deleted = false
        );
        val user =
            userRepository.findOne(Example.of(queryUser)).orElseThrow { QwerException(ErrorCode.SYSTEM_ERROR) }
        user.deleted = true
        userRepository.save(user)
        return true
    }

    fun update(request: UserUpdateRequest, userId: String): Boolean {
        val queryUser = User(
            id = request.id,
            deleted = false
        );
        val user =
            userRepository.findOne(Example.of(queryUser)).orElseThrow { QwerException(ErrorCode.SYSTEM_ERROR) }

        user.apply {
            username = request.username
            password = request.password
        }

        userRepository.save(user)
        return false;
    }

    fun page(request: UserPageRequest, userId: String): RespPage<UserPageResponse> {
        val queryUser = User(
            deleted = false
        );
        val page =
            userRepository.findAll(Example.of(queryUser), PageRequest.of(request.pageIndex - 1, request.pageSize))
        return RespPage(
            total = page.totalElements,
            list = page.content.map {
                UserPageResponse(
                    id = it.id,
                    username = it.username,
                    password = it.password,
                )

            }
        )
    }

    fun info(request: UserInfoRequest, userId: String?): UserInfoResponse {
        val queryUser = User(
            id = request.id,
            deleted = false
        );
        val user =
            userRepository.findOne(Example.of(queryUser)).orElseThrow { QwerException(ErrorCode.SYSTEM_ERROR) }
        return UserInfoResponse(
            id = user.id,
            username = user.username,
            password = user.password,
        )
    }

    fun detail(request: UserDetailRequest, userId: String?): UserDetailResponse {
        val queryUser = User(
            id = request.id,
            deleted = false
        );
        val user =
            userRepository.findOne(Example.of(queryUser)).orElseThrow { QwerException(ErrorCode.SYSTEM_ERROR) }
        return UserDetailResponse(
            id = user.id,
            username = user.username,
            password = user.password,
        )
    }

}