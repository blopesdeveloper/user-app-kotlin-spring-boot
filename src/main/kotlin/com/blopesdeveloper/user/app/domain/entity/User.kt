package com.blopesdeveloper.user.app.domain.entity

data class User(var id: String,
                var email: String,
                var password: String,
                var type: Type)

enum class Type {
    ADMIN,
    ARTIST,
    CONTRACTOR
}