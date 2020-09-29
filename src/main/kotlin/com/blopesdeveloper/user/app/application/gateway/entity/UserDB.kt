package com.blopesdeveloper.user.app.application.gateway.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class UserDB(var id: String,
                var email: String,
                var password: String,
                var type: TypeDB)

enum class TypeDB {
    ADMIN,
    ARTIST,
    CONTRACTOR
}