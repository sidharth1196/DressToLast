package com.hackathon.dresstolast.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.hackathon.dresstolast.R
import com.hackathon.dresstolast.databinding.FragmentSignInBinding
import com.hackathon.dresstolast.ui.MainActivity
import com.hackathon.dresstolast.ui.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    lateinit var parentActivity: MainActivity
    val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        parentActivity = activity as MainActivity
        // parentActivity.binding.appBarLayout.visibility = View.GONE
        initListeners()
        return binding.root
    }

    private fun initListeners() {
        binding.btnSignin.setOnClickListener {
            viewModel.signInWithEmailPassword(
                binding.tilEmail.editText?.text.toString(),
                binding.tilPassword.editText?.text.toString()
            ) {
                findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
            }
        }
        binding.tvSignup.setOnClickListener{
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }
}