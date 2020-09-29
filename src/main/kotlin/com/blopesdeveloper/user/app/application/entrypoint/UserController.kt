package com.blopesdeveloper.user.app.application.entrypoint

import com.blopesdeveloper.user.app.application.entrypoint.entity.UserApi
import com.blopesdeveloper.user.app.application.entrypoint.mapper.UserMapper
import com.blopesdeveloper.user.app.domain.usecase.DeleteByIdUseCase
import com.blopesdeveloper.user.app.domain.usecase.FindAllUseCase
import com.blopesdeveloper.user.app.domain.usecase.FindByIdUseCase
import com.blopesdeveloper.user.app.domain.usecase.SaveUseCase
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/user")
class UserController(@Autowired val findAllUseCase: FindAllUseCase,
                     @Autowired val saveUseCase: SaveUseCase,
                     @Autowired val findByIdUseCase: FindByIdUseCase,
                     @Autowired val deleteByIdUseCase: DeleteByIdUseCase){

    val mapper = Mappers.getMapper(UserMapper::class.java)

    @GetMapping
    fun findAll() : ResponseEntity<List<UserApi>> {
        val users = findAllUseCase.execute()
                .map { mapper.map(it) }
        return ResponseEntity.ok(users)
    }

    @PostMapping
    fun save(@RequestBody userApi: UserApi): ResponseEntity<UserApi> {
        val user = mapper.map(userApi)
        val userApiSaved = mapper.map(saveUseCase.execute(user))
        return ResponseEntity.created(URI("/user")).body(userApiSaved)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: String): ResponseEntity<UserApi>{
        val userApi = mapper.map(findByIdUseCase.execute(id))
        return ResponseEntity.ok(userApi)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable("id") id: String) = ResponseEntity.ok(deleteByIdUseCase.execute(id))

}