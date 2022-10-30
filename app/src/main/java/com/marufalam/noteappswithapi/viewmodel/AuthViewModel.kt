package com.marufalam.noteappswithapi.viewmodel

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheezycode.notesample.models.UserRequest
import com.cheezycode.notesample.models.UserResponse
import com.marufalam.noteappswithapi.repository.UserRepository
import com.marufalam.noteappswithapi.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.Address
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() =userRepository.userResponseLiveData

    fun registerUser(userRequest: UserRequest) {
        viewModelScope.launch {
            userRepository.registerUser(userRequest)
        }

    }

    fun loginUser(userRequest: UserRequest) {
        viewModelScope.launch {
            userRepository.loginUser(userRequest)
        }
    }
    fun validateCredentials(username:String,emailAddress: String,password:String):Pair<Boolean,String>{
        var result = Pair(true,"")
        println("1...............${username.toString()}")
        println("2...............${emailAddress.toString()}")
        println("3...............${password.toString()}")
        println("4...............${TextUtils.isEmpty(username)}")
        if (TextUtils.isEmpty(username)||TextUtils.isEmpty(emailAddress)||TextUtils.isEmpty(password)){
            result = Pair(false,"Please Provide the Credentials")
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
            //result = Pair(false,"Please Provide valid Email")
        }
        else if (password.length<=5){
            result = Pair(false,"Please length should be greater than 5")
        }
        return result
    }
}