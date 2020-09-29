package com.blopesdeveloper.user.app.domain.usecase

import com.blopesdeveloper.user.app.domain.entity.User
import com.blopesdeveloper.user.app.domain.gateway.UserGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SaveUseCase(@Autowired val userGateway: UserGateway){

    fun execute(user: User): User = userGateway.save(user)

}
