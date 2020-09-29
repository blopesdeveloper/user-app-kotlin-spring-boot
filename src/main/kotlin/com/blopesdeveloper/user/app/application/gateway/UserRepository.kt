package com.blopesdeveloper.user.app.application.gateway

import com.blopesdeveloper.user.app.application.gateway.entity.UserDB
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: MongoRepository<UserDB, String> {
}