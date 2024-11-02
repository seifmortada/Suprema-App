package com.seifmortada.applications.suprema.data.remote.request

data class UserLogin(
    val login_id: String,
    val password: String
)

data class RequestBody(
    val User: UserLogin
)
