package com.blopesdeveloper.user.app.application.gateway.mapper

import com.blopesdeveloper.user.app.application.gateway.entity.UserDB
import com.blopesdeveloper.user.app.domain.entity.User
import org.mapstruct.Mapper

@Mapper
interface UserMapper {

    fun map(user: User): UserDB

    fun map(userDB: UserDB): User

}