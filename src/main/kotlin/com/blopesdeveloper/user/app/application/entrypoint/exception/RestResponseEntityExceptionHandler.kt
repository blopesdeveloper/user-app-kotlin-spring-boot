package com.blopesdeveloper.user.app.application.entrypoint.exception

import com.blopesdeveloper.user.app.domain.exception.AlreadyExistsException
import com.blopesdeveloper.user.app.domain.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseEntityExceptionHandler: ResponseEntityExceptionHandler(){

    @ExceptionHandler(NotFoundException::class)
    fun createResponseNotFound(ex: NotFoundException, request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity(ex.newMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(AlreadyExistsException::class)
    fun createResponseBadRequest(ex: AlreadyExistsException, request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity(ex.newMessage, HttpStatus.BAD_REQUEST)
    }
}