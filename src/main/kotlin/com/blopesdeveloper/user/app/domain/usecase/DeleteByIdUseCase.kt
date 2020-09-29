package com.blopesdeveloper.user.app.domain.usecase

import com.blopesdeveloper.user.app.domain.gateway.UserGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DeleteByIdUseCase(@Autowired val userGateway: UserGateway){

    fun execute(id: String) = userGateway.deleteById(id)

}
