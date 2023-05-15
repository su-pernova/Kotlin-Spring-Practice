package com.example.mvc.annotation

import com.example.mvc.validator.StringFormatDateTimeValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

// 커스텀 어노테이션을 만들어 원하는 방식대로 동작하는 validation annotation 을 만들어보자
// 어노테이션으로 형식과 메세지를 지정해준 다음, 실제 검증을 진행하는 validator 를 따로 구현해줘야 한다.
// validator 를 구현한 다음 @Constraint 어노테이션을 이용해 어떤 validator 를 사용할 것인지 지정해줄 수 있다.

@Constraint(validatedBy = [StringFormatDateTimeValidator::class])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class StringFormatDateTime(
    val pattern: String = "yyyy-MM-dd HH:mm:ss",
    val message: String = "시간 형식이 유효하지 않습니다.",

    // 아래 두 내용은 일단 몰라도 된다.
    // 어노테이션 선언시 필수로 들어가는 부분
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)