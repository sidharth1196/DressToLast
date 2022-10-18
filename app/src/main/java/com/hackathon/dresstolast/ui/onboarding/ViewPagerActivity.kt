package com.hackathon.dresstolast.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.hackathon.dresstolast.R
import com.hackathon.dresstolast.ui.onboarding.screens.FirstScreen
import com.hackathon.dresstolast.ui.onboarding.screens.SecondScreen
import com.hackathon.dresstolast.ui.onboarding.screens.ThirdScreen
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        viewPager = findViewById(R.id.view_pager)

        val fragments: ArrayList<Fragment> = arrayListOf(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()

        )
        val adapter = ViewPagerAdapter(fragments, this)
        viewPager.adapter = adapter

    }
    override fun onBackPressed(){
        if(viewPager.currentItem == 0){
            super.onBackPressed()
        }else{
            viewPager.currentItem = view_pager.currentItem - 1
        }
    }
}