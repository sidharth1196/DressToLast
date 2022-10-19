package com.hackathon.dresstolast.ui.account

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.hackathon.dresstolast.R
import com.hackathon.dresstolast.databinding.FragmentSignUpBinding
import com.hackathon.dresstolast.ui.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        initListeners()
        return binding.root
    }

    private fun initListeners() {
        binding.btnSignUp.setOnClickListener {
            viewModel.createUserWithEmailPassword(
                binding.tilEmail.editText?.text.toString(),
                binding.tilPassword.editText?.text.toString(),
                binding.tilFullName.editText?.text.toString()
            ) {
                findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
            }
        }
    }

}