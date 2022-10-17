package com.hackathon.dresstolast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class OnboardingActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding2)

        supportActionBar!!.setTitle("Onboarding                             2/3")

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, OnboardingActivity3::class.java)
            startActivity(intent)
        }
    }
}
