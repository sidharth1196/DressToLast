package com.hackathon.dresstolast.ui.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hackathon.dresstolast.R
import kotlinx.android.synthetic.main.fragment_first_screen.*

class FirstScreen : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_screen, container, false)
//        val v = inflater.inflate(R.layout.fragment_first_screen, container, false)
//
//        button.setOnClickListener{
//            val secondFragment =
//        }
//        return v
    }
}