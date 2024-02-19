package com.example.myapplication.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/members/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/members/join")
    fun join(@Body joinRequest: JoinRequest): Call<JoinResponse>

    @POST("/members/email")
    fun checkEmail(@Body emailCheckRequest: EmailCheckRequest): Call<EmailCheckResponse>

    @POST("/members/nickname")
    fun checkNickname(@Body nicknameCheckRequest: NicknameCheckRequest): Call<NicknameCheckResponse>
}

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String)

data class JoinRequest(val email: String, val nickname: String, val password: String)
data class JoinResponse(val status: String)

data class EmailCheckRequest(val email: String)
data class EmailCheckResponse(val isAvailable: Boolean)

data class NicknameCheckRequest(val nickname: String)
data class NicknameCheckResponse(val isAvailable: Boolean)