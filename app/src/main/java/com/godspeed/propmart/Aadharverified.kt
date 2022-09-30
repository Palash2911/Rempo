package com.godspeed.propmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView

class Aadharverified : AppCompatActivity() {

    lateinit var lottie:LottieAnimationView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aadharverified)

        lottie = findViewById(R.id.animation);
        lottie.playAnimation();
        val btn = findViewById<Button>(R.id.visithome)
        btn.setOnClickListener {
            val intent = Intent(this, Bottomtab::class.java)
            startActivity(intent)
            finish()
        }
    }
}