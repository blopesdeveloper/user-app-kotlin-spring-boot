package com.blopesdeveloper.user.app.domain.gateway

import com.blopesdeveloper.user.app.domain.entity.User

interface UserGateway {

    fun save(user: User): User

    fun deleteById(id: String)

    fun findById(id: String): User?

    fun findAll(): List<User>

}