package com.hackathon.dresstolast.ui.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.hackathon.dresstolast.R
import com.hackathon.dresstolast.databinding.FragmentSecondScreenBinding
import com.hackathon.dresstolast.ui.onboarding.ViewPagerActivity

class SecondScreen : Fragment() {
    private lateinit var binding: FragmentSecondScreenBinding
    lateinit var parentActivity : ViewPagerActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second_screen, container, false)
        parentActivity = activity as ViewPagerActivity
        binding.button.setOnClickListener {
            val viewPager = parentActivity.findViewById<ViewPager2>(R.id.view_pager)
            viewPager.currentItem = 2
        }
        return binding.root
    }

}