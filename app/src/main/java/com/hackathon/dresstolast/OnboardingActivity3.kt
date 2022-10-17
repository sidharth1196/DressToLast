package com.hackathon.dresstolast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class OnboardingActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding3)

        supportActionBar!!.setTitle("Onboarding                             3/3")

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}