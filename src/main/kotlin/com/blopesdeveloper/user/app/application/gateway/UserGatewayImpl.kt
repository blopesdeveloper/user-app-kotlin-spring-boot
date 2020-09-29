package com.blopesdeveloper.user.app.application.gateway

import com.blopesdeveloper.user.app.application.gateway.mapper.UserMapper
import com.blopesdeveloper.user.app.domain.entity.User
import com.blopesdeveloper.user.app.domain.exception.NotFoundException
import com.blopesdeveloper.user.app.domain.gateway.UserGateway
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
 class UserGatewayImpl(@Autowired val userRepository: UserRepository) : UserGateway {

    val mapper = Mappers.getMapper(UserMapper::class.java)

    override fun save(user: User): User {
        val userDB = mapper.map(user)
        userRepository.save(userDB)
        return user
    }

    override fun deleteById(id: String) = userRepository.deleteById(id)


    override fun findById(id: String) =
            userRepository.findById(id)
                .map { mapper.map(it) }
                .orElse(null)


    override fun findAll(): List<User> {
        return userRepository.findAll()
                .map { mapper.map(it) }
                .toList()
    }
}