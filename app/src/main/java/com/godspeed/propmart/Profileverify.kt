package com.godspeed.propmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContentProviderCompat.requireContext

class Profileverify : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profileverify)

        val btn = findViewById<Button>(R.id.profilevisithome)

        btn.setOnClickListener {
            val intent = Intent(this, Bottomtab::class.java)
            startActivity(intent)
        }
    }
}