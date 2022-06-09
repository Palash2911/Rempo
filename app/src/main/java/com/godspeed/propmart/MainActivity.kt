package com.godspeed.propmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FcmBroadcastProcessor.reset
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val TAG = "Login"
    private val auth = FirebaseAuth.getInstance()
    private var storedVerificationId: String = ""
    private val db = Firebase.firestore
    private lateinit var  messaging: FirebaseMessaging
    lateinit var phone: EditText

    override fun onStart() {
        super.onStart()
        messaging = FirebaseMessaging.getInstance();
        if (auth.currentUser != null) {
            db.collection("Users").document(auth.currentUser!!.uid).get()
                .addOnCompleteListener { task2 ->
                    if (task2.result?.exists() == true) {
                        val intent = Intent(this, Bottomtab::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        val intent = Intent(this, Profile::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
        } else {
            Toast.makeText(this,"Welcome User !", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.godspeed.propmart.R.layout.activity_main)

        val get = findViewById<Button>(R.id.sendotp)
        phone = findViewById<EditText>(R.id.phone)

        get.setOnClickListener{
            if(phone.text.toString().length<10 || phone.text.toString().isEmpty())
            {
                Toast.makeText(this, "Enter A Valid Number !!", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this, Otpactivity::class.java)
                intent.putExtra("Number", phone.text.toString())
                Toast.makeText(this, "OTP Sent Successfully!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }
        }
    }
}