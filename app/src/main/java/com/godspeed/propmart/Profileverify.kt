package com.godspeed.propmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Profileverify : AppCompatActivity() {
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profileverify)

        val btn = findViewById<Button>(R.id.profilevisithome)

        btn.setOnClickListener {
            db.collection("Users").document(Firebase.auth.currentUser?.uid.toString()).get().addOnSuccessListener {
                if(it.get("Account") == "Buyer")
                {
                    val intent = Intent(this,Bottomtab::class.java);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    val intent = Intent(this,BottomnavSeller::class.java);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }
}