package com.godspeed.propmart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Visitweb : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visitweb)
        val btn = findViewById<Button>(R.id.visitWeb)
        btn.setOnClickListener {
            val intent = Intent(this , BottomnavSeller::class.java);
            startActivity(intent);
        }
    }
}