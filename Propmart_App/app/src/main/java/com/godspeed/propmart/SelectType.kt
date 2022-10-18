package com.godspeed.propmart

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SelectType: AppCompatActivity() {
    private val db = Firebase.firestore
    var radiogrp: RadioGroup? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_type)

        val selectbtn = findViewById<Button>(R.id.proceed)
        val name = findViewById<TextView>(R.id.name)
        val pb = findViewById<ProgressBar>(R.id.selectprogress)
        val ll = findViewById<LinearLayout>(R.id.selectll)
        val buy = findViewById<RadioButton>(R.id.buyerAcc)
        radiogrp = findViewById(R.id.account_type)
        val sell = findViewById<RadioButton>(R.id.sellerAc)
        val skipbtn = findViewById<TextView>(R.id.skipbtn)

        db.collection("Users").document(Firebase.auth.currentUser?.uid.toString())
            .get().addOnSuccessListener {
                name.text = it["Name"].toString()
            }.addOnFailureListener {
                Toast.makeText(this, "Something Went Wrong !", Toast.LENGTH_SHORT).show()
            }
        selectbtn.setOnClickListener {
            val intSelectButton: Int = radiogrp!!.checkedRadioButtonId
            Log.d("tagooo", intSelectButton.toString())
            pb.visibility = VISIBLE
            ll.visibility = GONE
            when (intSelectButton) {
                R.id.buyradiobtn -> {
                    val intent = Intent(this, Bottomtab::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.sellradiobtn -> {
                    db.collection("Users").document(Firebase.auth.currentUser?.uid.toString())
                        .update("Account", "Seller").addOnSuccessListener {
                            Toast.makeText(this, "Welcome !", Toast.LENGTH_SHORT).show()
                        }
                    val intent = Intent(this, BottomnavSeller::class.java)
                    startActivity(intent)
                    finish()
                }
                else -> {
                    pb.visibility = GONE
                    ll.visibility = VISIBLE
                    Toast.makeText(this, "Please Make a Choice !", Toast.LENGTH_SHORT).show()
                }
            }
        }
        skipbtn.setOnClickListener{
            val intent = Intent(this, Bottomtab::class.java)
            startActivity(intent)
            finish()
        }
    }
}