package com.example.mvc.controller.delete

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

// 현재 DB에 연결되어 있는 상태가 아니므로
// path variable, request param 사용을 복습할 겸 delete function 을 작성해보자

@RestController
@RequestMapping("/api")
@Validated // 이 Annotation 을 달아주어야 하위의 validating annotation 들이 동작한다.
class DeleteApiController {

    @DeleteMapping(path = ["/delete-mapping"])
    fun deleteMapping (
        @RequestParam(value = "name") _name : String,

        @NotNull(message = "age 값이 null 입니다.") // validating annotation 1
        @Min(value = 20, message = "age는 20 보다 커야 합니다.") // validating annotation 2
        @RequestParam(value = "age") _age : Int
    ): String {
        println(_name)
        println(_age)
        return "$_name $_age"
    }

    @DeleteMapping(path=["/delete-mapping/name/{name}/age/{age}"])
    fun deleteMappingPath(
        @Size(min = 2, max = 5, message = "name 의 길이는 2에서 5 사이여야 합니다.") // 문자열의 길이를 지정해줄 수 있다.
        @NotNull
        @PathVariable(value = "name") _name: String,

        @NotNull(message = "age 값이 null 입니다.") // validating annotation 1
        @Min(value = 20, message = "age는 20 보다 커야 합니다.") // validating annotation 2
        @PathVariable(value = "age") _age: Int
    ): String {
        println(_name)
        println(_age)
        return "$_name $_age"
    }
}