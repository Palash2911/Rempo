package com.godspeed.propmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Bidplaced : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bidplaced)

        val btn = findViewById<Button>(R.id.bidvisithome)
        btn.setOnClickListener {
            val intent = Intent(this, Bottomtab::class.java)
            startActivity(intent)
        }
    }
}