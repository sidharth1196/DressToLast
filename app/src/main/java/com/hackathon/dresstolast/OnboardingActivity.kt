package com.hackathon.dresstolast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Context
import android.content.Intent
import android.widget.Button

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        supportActionBar!!.setTitle("Onboarding                                     1/3")

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener{
            val intent = Intent(this, OnboardingActivity2::class.java)
            startActivity(intent)
        }





    }
}