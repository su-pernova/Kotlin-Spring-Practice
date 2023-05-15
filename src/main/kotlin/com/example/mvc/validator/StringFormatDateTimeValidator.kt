package com.example.mvc.validator

import com.example.mvc.annotation.StringFormatDateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class StringFormatDateTimeValidator: ConstraintValidator<StringFormatDateTime, String> {

    private var pattern : String ?= null

    override fun initialize(constraintAnnotation: StringFormatDateTime?) {
        this.pattern = constraintAnnotation?.pattern // 어노테이션에서 지정했던 패턴을 현재 클래스의 pattern 에 할당
    }

    // isValid 라는 메소드를 반드시 오버라이드 해야 한다.
    // 정상이면 true, 비정상이면 false 반환
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return try {
            LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern))
            return true
        } catch(e: Exception) {
            return false
        }
    }

}