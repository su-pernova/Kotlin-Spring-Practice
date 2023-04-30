package com.example.mvc.controller.post

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PostApiController {

    @PostMapping("/post-mapping")
    fun postMapping(): String {
        return "post-mapping"
    }

    // @PostMapping 어노테이션 대신 @RequestMapping 어노테이션을 사용하는 경우
    // GET 메소드에서도 'request-mapping' UTI 주소를 사용했지만, 메소드 형식이 POST 로 다르기 때문에 충돌이 발생하지 않는다.
    @RequestMapping(method = [RequestMethod.POST], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    // Object Mapper 라이브러리를 사용해서 요청을 받아보자.
    // Object Mapper : json -> object 로, object -> json 으로 바꿔주는 라이브러리
    // Request Body 로 Request 를 받고, 받은 내용을 그대로 반환하는 함수
    @PostMapping("/post-mapping/object")
    fun postMappingObject(@RequestBody userRequest: UserRequest): UserRequest { // Request : json -> object
        println(userRequest)
        return userRequest // Response : object -> json
    }

}