package com.seifmortada.applications.suprema.data.remote.apis

import com.seifmortada.applications.suprema.data.remote.response.ApiResponse
import com.seifmortada.applications.suprema.data.remote.request.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
        @POST("login")
        suspend fun login(
            @Body data: RequestBody
        ):Response<ApiResponse>
    }