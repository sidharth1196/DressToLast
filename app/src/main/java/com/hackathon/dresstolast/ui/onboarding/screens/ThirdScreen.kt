package com.hackathon.dresstolast.ui.onboarding.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.hackathon.dresstolast.R
import com.hackathon.dresstolast.databinding.FragmentThirdScreenBinding
import com.hackathon.dresstolast.ui.MainActivity
import com.hackathon.dresstolast.ui.onboarding.ViewPagerActivity


class ThirdScreen : Fragment() {
    private lateinit var binding: FragmentThirdScreenBinding
    lateinit var parentActivity: ViewPagerActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_third_screen, container, false)
        binding.button.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            parentActivity = activity as ViewPagerActivity
            startActivity(intent)
            parentActivity.finish()
        }
        return binding.root
    }


}