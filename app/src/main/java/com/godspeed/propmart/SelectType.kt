package com.godspeed.propmart

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SelectType: AppCompatActivity() {
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_type)
        val radioGroup = findViewById<RadioGroup>(R.id.account_type)

        val selectbtn = findViewById<Button>(R.id.proceed)
        selectbtn.setOnClickListener {
            var id = radioGroup.checkedRadioButtonId
            Log.d("IDSS", id.toString())
            val intent = Intent(this, Profileverify::class.java)
            startActivity(intent)
            finish()
        }
    }
}