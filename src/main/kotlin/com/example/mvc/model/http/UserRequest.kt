package com.example.mvc.model.http

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming


// 필드의 개수가 20개가 넘어가는 경우, 각 필드에 일일이 @JsonProperty 를 지정해 주는 것은 매우 번거로울 수 있다.
// 이 때 @JsonNaming 어노테이션을 사용하면 클래스의 모든 필드에 대한 naming strategy 를 지정해줄 수 있다.
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserRequest (
    var name: String? = null,
    var age: Int? = null,
    var email: String? = null,
    var address: String? = null,

    // @JsonProperty 어노테이션을 사용하면 코드 내에서는 phoneNumber 라는 이름을 사용하지만 (CamelCase)
    // Json 요청을 받고 응답을 보낼 때에는 필드 값을 phone_number 라는 이름으로 표시할 수 있다.
    // @JsonProperty("phone_number")
    var phoneNumber: String? = null
)