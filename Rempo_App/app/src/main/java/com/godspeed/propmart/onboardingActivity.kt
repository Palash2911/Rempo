package com.godspeed.propmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.FragmentManager
import com.ramotion.paperonboarding.PaperOnboardingFragment
import com.ramotion.paperonboarding.PaperOnboardingPage

class onboardingActivity : AppCompatActivity() {

    private lateinit var fragmentManager:FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        fragmentManager = supportFragmentManager;

        val pages = arrayListOf<PaperOnboardingPage>();
        val welcome:PaperOnboardingPage = PaperOnboardingPage("Welcome","Start Exploring new Properties",0,0,0)
//        pages.add()

//        val paperOnboardingFragment:PaperOnboardingFragment =
//            PaperOnboardingFragment.newInstance()

    }
}