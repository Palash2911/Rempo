package com.godspeed.propmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Contact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        val btn = findViewById<Button>(R.id.continueContact)
        btn.setOnClickListener {
            val intent = Intent(this, Bottomtab::class.java)
            startActivity(intent)
            finish()
        }
    }
}