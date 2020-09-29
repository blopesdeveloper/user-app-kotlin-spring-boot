package com.blopesdeveloper.user.app.application.entrypoint.entity

data class UserApi(var id: String,
                  var email: String,
                  var password: String,
                  var type: TypeApi)

enum class TypeApi {
    ADMIN,
    ARTIST,
    CONTRACTOR
}