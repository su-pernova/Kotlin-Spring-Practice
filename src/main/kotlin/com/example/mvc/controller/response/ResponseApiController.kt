package com.example.mvc.controller.response

import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

// Http Status Code : 200 대 코드 외 다른 범위의 코드를 내리는 연습을 해보자.

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    // 1. GET 4XX Error
    @GetMapping
    fun getMapping1(@RequestParam age: Int?): ResponseEntity<String> {
        // 1. age == null 이면 400 error
        if(age == null) {
            return ResponseEntity.status(400).body("age 값이 null 입니다.")
        }

        // 2. age < 20 이면 400 error
        if(age < 20) {
            return ResponseEntity.status(400).body("age 값은 20 보다 커야 합니다.")
        }

        return ResponseEntity.ok("OK")
    }

    // 1-1. 위 코드를 좀 더 코틀린 스럽게 수정해보자
    @GetMapping("/kotlin-like")
    fun getMapping2(@RequestParam age: Int?): ResponseEntity<String> {
        return age?.let {
            // age != null
            if(it < 20) {
                return ResponseEntity.status(400).body("age 값은 20 보다 커야 합니다.")
            }
            ResponseEntity.ok("OK")
        }?: kotlin.run {
            // age == null
            return ResponseEntity.status(400).body("age 값이 null 입니다.")
        }
    }

    // 2. POST 200
    @PostMapping
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {
        return ResponseEntity.status(200).body(userRequest)
    }

    // 3. PUT 201
    @PutMapping
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    // 4. DELETE 500
    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id: Int): ResponseEntity<Any> {
        return ResponseEntity.status(500).body(null)
    }
}