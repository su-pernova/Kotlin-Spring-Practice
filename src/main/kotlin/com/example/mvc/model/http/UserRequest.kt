package com.example.mvc.model.http

import com.example.mvc.annotation.StringFormatDateTime
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.constraints.*


// 필드의 개수가 20개가 넘어가는 경우, 각 필드에 일일이 @JsonProperty 를 지정해 주는 것은 번거로울 수 있다.
// 이 때 @JsonNaming 어노테이션을 사용하면 클래스의 모든 필드에 대한 naming strategy 를 지정해줄 수 있다.
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserRequest (
    // Kotlin 에서는 Java 와 달리 validation 을 작성할 때 filed 키워드를 사용한다.
    @field:NotEmpty
    @field:Size(min = 2, max = 8)
    var name: String? = null,

    @field:PositiveOrZero // 0 이상
    var age: Int? = null,

    @field:Email
    var email: String? = null,

    @field:NotBlank
    var address: String? = null,

    // @JsonProperty 어노테이션을 사용하면 코드 내에서는 phoneNumber 라는 이름을 사용하지만 (CamelCase)
    // Json 요청을 받고 응답을 보낼 때에는 필드 값을 phone_number 라는 이름으로 표시할 수 있다.
    // @JsonProperty("phone_number")
    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$") // 휴대폰 번호 형식을 검증하는 정규식
    var phoneNumber: String? = null,

    // 직접 만든 어노테이션을 사용해보자.
    @field:StringFormatDateTime
    var createdAt: String? = null
){

//    // Validation 어노테이션으로 지원되지 않는 부분을 검증하고자 할 때는 아래처럼 직접 함수를 작성할 수도 있다.
//    @AssertTrue(message = "생성일자의 패턴은 yyyy-mm-dd hh:mm:ss 여야 합니다.")
//    private fun isValidCreatedAt(): Boolean { // 정상 : true, 비정상 : false 반환
//        try {
//            LocalDateTime.parse(this.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
//            return true
//        } catch (e: Exception){
//            return false
//        }
//    }

}