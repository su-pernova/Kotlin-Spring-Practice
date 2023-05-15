package com.example.mvc.advice

import com.example.mvc.controller.exception.ExceptionApiController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

// @RestControllerAdvice 어노테이션을 달아주면
// RestController 에서 발생하는 Exception 들이 이 클래스를 통과하게 된다.
// 이 방법을 사용하면 try-catch 문을 사용하지 않아도 예외를 처리할 수 있다.

// basePackageClasses 옵션을 사용하면 특정 컨트롤러만 ControllerAdvice 를 타도록 제한하는 것이 가능하다.
// @RestControllerAdvice(basePackageClasses = [ExceptionApiController::class])
class GlobalControllerAdvice {

    @ExceptionHandler(value = [RuntimeException::class])
    fun exception(e: RuntimeException): String {
        return "Server Error 입니다."
    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundException(e: IndexOutOfBoundsException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }

}