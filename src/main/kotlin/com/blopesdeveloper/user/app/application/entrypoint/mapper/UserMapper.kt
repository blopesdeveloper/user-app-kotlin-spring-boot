package com.blopesdeveloper.user.app.application.entrypoint.mapper

import com.blopesdeveloper.user.app.application.entrypoint.entity.UserApi
import com.blopesdeveloper.user.app.domain.entity.User
import org.mapstruct.Mapper

@Mapper
interface UserMapper {

    fun map(user: User): UserApi

    fun map(user: UserApi): User
}