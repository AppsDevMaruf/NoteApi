package com.marufalam.noteappswithapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cheezycode.notesample.models.UserRequest
import com.marufalam.noteappswithapi.databinding.FragmentRegisterBinding
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
        binding.btnLogin.setOnClickListener {
            // findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            authViewModel.loginUser(UserRequest("maruf6@gmail.com", "maruf786@", "maruf"))
        }
        binding.btnSignUp.setOnClickListener {
        authViewModel.registerUser(UserRequest("maruf6@gmail.com", "maruf786@", "maruf"))

            //findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}