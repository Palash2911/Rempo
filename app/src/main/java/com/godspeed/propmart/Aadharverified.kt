package com.godspeed.propmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Aadharverified : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aadharverified)

        val btn = findViewById<Button>(R.id.visithome)
        btn.setOnClickListener {
            val intent = Intent(this, Bottomtab::class.java)
            startActivity(intent)
            finish()
        }
    }
}