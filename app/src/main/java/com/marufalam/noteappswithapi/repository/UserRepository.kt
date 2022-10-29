package com.marufalam.noteappswithapi.repository

import android.util.Log
import com.cheezycode.notesample.models.UserRequest
import com.marufalam.noteappswithapi.api.UserApi
import com.marufalam.noteappswithapi.utils.Constants.TAG
import javax.inject.Inject

class UserRepository @Inject constructor(private val userApi: UserApi) {
    suspend fun registerUser(userRequest: UserRequest){
        val response = userApi.signup(userRequest)
        Log.d(TAG, "registerUser: ${response.body().toString()}")

    }
    suspend fun loginUser(userRequest: UserRequest){
        val response = userApi.signin(userRequest)
        Log.d(TAG, "LoginUser: ${response.body().toString()}")
    }

}