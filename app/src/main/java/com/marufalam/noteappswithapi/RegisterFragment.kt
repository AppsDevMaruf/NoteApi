package com.marufalam.noteappswithapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cheezycode.notesample.models.UserRequest
import com.marufalam.noteappswithapi.databinding.FragmentRegisterBinding
import com.marufalam.noteappswithapi.utils.NetworkResult
import com.marufalam.noteappswithapi.viewmodel.AuthViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<AuthViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.btnSignUp.setOnClickListener {
            val validateResult = validateUserInput()
            if (validateResult.first){
                authViewModel.registerUser(getUserRequest())

            }else{
                binding.txtError.text = validateResult.second

            }

        }
        bindObservers()

    }
    private fun getUserRequest():UserRequest{
        val emailAddress = binding.txtEmail.toString().trim()
        val password = binding.txtPassword.toString().trim()
        val username = binding.txtUsername.toString().trim()
        println("11...............${username.toString()}")
        println("22...............${emailAddress.toString()}")
        println("33...............${password.toString()}")
        return UserRequest(emailAddress,password,username)
    }

    private fun validateUserInput(): Pair<Boolean, String> {
       val userRequest = getUserRequest()
        return authViewModel.validateCredentials(userRequest.username,userRequest.email,userRequest.password)

    }

    private fun bindObservers() {
        authViewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    //token work will working here...
                    findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
                }
                is NetworkResult.Error -> {
                    binding.txtError.text = it.message
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}