package com.qwer.config

import com.qwer.api.resp.base.RespBody
import com.qwer.exception.QwerException
import com.qwer.util.ErrorCode
import jakarta.validation.ConstraintViolationException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class QwerExceptionAdvice {
    @ExceptionHandler(Exception::class)
    fun handleRunTimeException(e: Exception?): ResponseEntity<*> {
        logger.error("system exception", e)
        return ResponseEntity.ok<Any>(ERROR)
    }

    @ExceptionHandler(QwerException::class)
    fun handleRunTimeException(e: QwerException): ResponseEntity<*> {
        logger.error("ryc exception", e)
        return if (e.getErrorCode() != null) {
            ResponseEntity.ok(RespBody.fail(e.getErrorCode()!!))
        } else ResponseEntity.ok(RespBody.fail(ErrorCode.SYSTEM_ERROR.value, e.message))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleRunTimeException(e: IllegalArgumentException): ResponseEntity<Any> {
        logger.error("IllegalArgumentException {}", e)
        return ResponseEntity.ok(RespBody.fail(ErrorCode.ILLEGAL_ARGUMENT_ERROR.value, e.message))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<*> {
        logger.error("MethodArgumentNotValidException {} ", e.bindingResult.fieldErrors)
        return ResponseEntity.ok(
            RespBody.fail(
                ErrorCode.ILLEGAL_ARGUMENT_ERROR.value,
                e.bindingResult.fieldErrors.stream().findFirst().get().defaultMessage
            )
        )
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(e: ConstraintViolationException): ResponseEntity<*> {
        logger.error("ConstraintViolationException {} ", e.constraintViolations.stream().findFirst().get().message)
        return ResponseEntity.ok(
            RespBody.fail(
                ErrorCode.ILLEGAL_ARGUMENT_ERROR.value,
                e.constraintViolations.stream().findFirst().get().message
            )
        )
    }

    companion object {
        private val logger = LoggerFactory.getLogger(QwerExceptionAdvice::class.java)
        private val ERROR = RespBody.fail(ErrorCode.ERROR)
    }
}