package com.example.mvc.controller.put

import com.example.mvc.model.http.Result
import com.example.mvc.model.http.UserRequest
import com.example.mvc.model.http.UserResponse
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

// PUT 은 DB 에 이미 해당 내용이 있으면 수정을, 없으면 생성을 처리한다.
@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping():String {
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping-put-method"
    }

    @PutMapping(path = ["/put-mapping/object"])
    fun putMappingObject1(@RequestBody userRequest: UserRequest): UserRequest {
        return userRequest
    }

    @PutMapping(path = ["/put-mapping/object/detail"])
    fun putMappingObject2(@RequestBody userRequest: UserRequest): UserResponse {
        // Response
        // apply 패턴 사용
        return UserResponse().apply {
            // 1. result
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }
        }.apply {
            // 2. description
            this.description = "description"
        }.apply {
            // 3. user mutable list
            val userList = mutableListOf<UserRequest>()
            userList.add(userRequest)
            userList.add(UserRequest().apply {
                this.name = "a"
                this.age = 10
                this.email = "a@gmail.com"
                this.address = "a address"
                this.phoneNumber = "010-1234-1234"
            })
            userList.add(UserRequest().apply {
                this.name = "b"
                this.age = 20
                this.email = "b@gmail.com"
                this.address = "b address"
                this.phoneNumber = "010-1234-1234"
            })
            this.user = userList
        }
    }



}