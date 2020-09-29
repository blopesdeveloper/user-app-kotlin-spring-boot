package com.blopesdeveloper.user.app.domain.usecase

import com.blopesdeveloper.user.app.domain.entity.User
import com.blopesdeveloper.user.app.domain.exception.NoContentException
import com.blopesdeveloper.user.app.domain.exception.NotFoundException
import com.blopesdeveloper.user.app.domain.gateway.UserGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FindAllUseCase(@Autowired val userGateway: UserGateway){

    fun execute(): List<User>  {
        val list = userGateway.findAll()
         return if (list.isNotEmpty()) list else throw NotFoundException("there are not users")
    }
}
