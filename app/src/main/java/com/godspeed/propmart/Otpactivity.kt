package com.godspeed.propmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.common.base.Verify.verify
import com.google.firebase.auth.FirebaseAuth

class Otpactivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpactivity)

        val otp = intent.extras?.get("OTP").toString()
        val otpview = findViewById<EditText>(R.id.otp_view)
        val verify = findViewById<Button>(R.id.verifyotp)
        val editnum = findViewById<TextView>(R.id.editphoneno)
        Log.d("OTP123", otp)
        verify.setOnClickListener {
            if(otpview.text.toString()==otp)
            {
                val intent = Intent(this, Profile::class.java)
                startActivity(intent)
                finish()
            }
            else
            {
                Toast.makeText(this, "Please Enter Correct OTP !", Toast.LENGTH_SHORT).show()
            }
        }

        editnum.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}