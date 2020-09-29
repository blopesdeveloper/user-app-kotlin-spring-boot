package com.blopesdeveloper.user.app.domain.usecase

import com.blopesdeveloper.user.app.domain.entity.User
import com.blopesdeveloper.user.app.domain.exception.NotFoundException
import com.blopesdeveloper.user.app.domain.gateway.UserGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FindByIdUseCase(@Autowired val gateway: UserGateway){

    fun execute(id: String): User = gateway.findById(id) ?: throw NotFoundException("item ${id} not found")
}
