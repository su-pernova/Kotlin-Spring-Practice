package com.example.mvc.controller.get

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController // REST API Controller로 동작함을 명시
@RequestMapping("/api") // 해당 컨트롤러는 http://localhost:8080/api 의 URI 형태로 노출된다.
class GetApiController {

    // 요즘 사용하는 방식
    @GetMapping("/hello") // GET http://localhost:8080/api/hello
    // @GetMapping(path = ["/hello", "/hi"]) // 이렇게 여러개의 주소를 할당할 수도 있다.
    fun hello(): String {
        return "hello kotlin"
    }

    // 과거에 사용하던 방식
    @RequestMapping(method = [RequestMethod.GET], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    // 예) GET http://localhost:8080/api/get-mapping/path-variable/steve
    @GetMapping("/get-mapping/path-variable/{name}")
    fun pathVariable1(@PathVariable name: String): String {
        println(name)
        return name
    }

    // PathVariable을 여러개 둘 수도 있다.
    @GetMapping("/get-mapping/path-variable/{name}/{age}")
    fun pathVariable2(@PathVariable name: String, @PathVariable age: Int): String {
        println("${name}, ${age}")
        return "$name $age"
    }

    // @PathVariable(value = "name") 식으로 매칭이 가능하다.
    // PathVariable에서 사용한 변수명을 함수 내부에서도 사용하고 싶을 때 활용 가능
    @GetMapping("/get-mapping/query-param")
    fun queryParam(
        @RequestParam(value = "name") inputName: String,
        @RequestParam(value = "age") inputAge: Int
    ): String {
        val name = "steve"
        return "$name $inputName $inputAge"
    }

    // queryParameter를 객체로 Mapping 하는 방법
    // 스프링에서 @RestController 를 사용하는 경우
    // Return Type이 Object인 경우 결과는 JSON format에 매핑된다.
    // 단 Kotlin 에선 변수명에 하이픈(-)이 들어갈 수 없으므로, 설계시 주의 필요
    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

    // Map 형식으로 받는 방법
    // Map : Key-value 쌍의 데이터 타입 (파이썬 딕셔너리와 동일)
    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map: Map<String,Any>): Any? {
        println(map)
        val phoneNumber = map.get("phone-number")
        return phoneNumber
    }

}