package com.example.mvc.controller.page

import com.example.mvc.model.http.UserRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

// @Controller 어노테이션을 사용하면
// 'main/resources/static' 디렉토리 하위의 .html 파일을 찾아 띄워준다.

@Controller
class PageController {

    @GetMapping("/main")
    fun main(): String {
        println("init main")
        return "main.html" // "main.html" 이라는 문자열이 아닌 main.html 파일이 return 되는 것
    }

    // 하나의 클래스에서 @Controller 와 @RestController 의 동작을 혼합하여 사용하고 싶은 경우
    // @ResponseBody 어노테이션을 사용할 수 있다.
    @ResponseBody
    @GetMapping("/test1")
    fun response1(): String {
        return "main.html"
    }

    @ResponseBody // 이 어노테이션이 없으면 500 에러가 발생한다.
    @GetMapping("/test2")
    fun response2(): UserRequest {
        return UserRequest().apply {
            this.name = "steve"
        }
    }

}