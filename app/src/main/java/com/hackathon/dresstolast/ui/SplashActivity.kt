package com.hackathon.dresstolast.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.coroutineScope
import com.hackathon.dresstolast.R
import com.hackathon.dresstolast.model.Brand
import com.hackathon.dresstolast.model.ReviewQuestion
import com.hackathon.dresstolast.ui.viewModel.LoadingViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel by viewModel<LoadingViewModel>()

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
        window.statusBarColor = ContextCompat.getColor(this, R.color.app_background)
        val sharedPreference = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()

        lifecycle.coroutineScope.launch {
            delay(SPLASH_TIME)
            fetchData()
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

    private fun fetchData() {
        saveReviewQuestionsToDb(viewModel.RQList)
        saveBrandsToDB(viewModel.BrandList)
    }

    private fun saveBrandsToDB(brandList: List<Brand>) {
        brandList.forEach {
            val imageUrl = when {
                it.durabilityIndex < 5.1 -> "index_fragile"
                it.durabilityIndex < 8.1 -> "index_reliable"
                else -> "index_durable"
            }
            it.imageRes = resources.getIdentifier(imageUrl, "drawable", baseContext.packageName)
            viewModel.insertBrands(it)
        }
    }

    private fun saveReviewQuestionsToDb(reviewQuestions: List<ReviewQuestion>) {
        reviewQuestions.forEach {
            it.imageRes = resources.getIdentifier(it.imageUrl, "drawable", baseContext.packageName)
            viewModel.insertReviewQuestion(it)
        }
    }
}