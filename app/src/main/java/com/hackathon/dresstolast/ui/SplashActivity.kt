package com.hackathon.dresstolast.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.coroutineScope
import com.hackathon.dresstolast.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    /** Duration of wait  */
    companion object {
        private const val SPLASH_TIME = 1500L
        private const val PREFERENCE_NAME = "myPref"
        private const val KEY_ONBOARDING_INTRO = "showOnboardingIntro"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        val sharedPreference = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()

        lifecycle.coroutineScope.launch {
            delay(SPLASH_TIME)
            val intent = if (sharedPreference.getBoolean(KEY_ONBOARDING_INTRO, false)){
                Log.d("DTL", "onboarding true")
                Intent(this@SplashActivity, MainActivity::class.java)
            } else {
                editor.putBoolean(KEY_ONBOARDING_INTRO, true).apply()
                Log.d("DTL", "onboarding false")
                // Replace with Onboarding intro activity
                Intent(this@SplashActivity, MainActivity::class.java)
            }
            startActivity(intent)
            finish()
        }
    }
}