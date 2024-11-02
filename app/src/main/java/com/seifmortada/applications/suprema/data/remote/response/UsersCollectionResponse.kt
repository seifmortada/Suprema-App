package com.seifmortada.applications.suprema.data.remote.response


data class UsersCollectionResponse(
    val UserCollection: UserCollection,
    val Response: Response
)

data class UserCollection(
    val total: String,
    val rows: List<User>
)