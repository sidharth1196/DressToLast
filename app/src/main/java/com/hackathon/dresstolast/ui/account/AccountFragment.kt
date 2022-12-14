package com.hackathon.dresstolast.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hackathon.dresstolast.R
import com.hackathon.dresstolast.databinding.FragmentAccountBinding
import com.hackathon.dresstolast.ui.MainActivity
import com.hackathon.dresstolast.ui.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    val viewModel by viewModel<MainViewModel>()
    lateinit var parentActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
        parentActivity = activity as MainActivity
        parentActivity.setToolbarVisibility(View.GONE)
        viewModel.getCurrentUserDetails()
        initListeners()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        viewModel.currentUser.observe(viewLifecycleOwner, Observer {
            it.apply {
                binding.tvName.text = name
                binding.tvEmail.text = email
            }
        })
    }

    private fun initListeners() {
        binding.btnLogout.setOnClickListener {
            viewModel.logoutUser()
            findNavController().navigate(R.id.action_accountFragment_to_homeFragment)
        }
    }
}