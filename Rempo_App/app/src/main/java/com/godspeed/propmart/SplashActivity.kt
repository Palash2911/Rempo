package com.godspeed.propmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;
    private lateinit var firestore: FirebaseFirestore;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        Handler(Looper.getMainLooper()).postDelayed({
            navigateUser();
        },3000)
    }

    private fun navigateUser() {
        if(auth.currentUser != null){
            val userRef = firestore.collection("Users")
                .document(auth.uid.toString());
            userRef.get().addOnSuccessListener {
                if(it.getBoolean("Verified")==true){
                    Log.d("Testing", it.toString())
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
                else{
                    val intent = Intent(this,Bottomtab::class.java);
                    startActivity(intent);
                    finish();
//                  val intent = Intent(this,Uploadaadhar::class.java);
//                  startActivity(intent);
//                  finish();
                }
            }
        }
        else{
            val intent = Intent(this,MainActivity::class.java);
            startActivity(intent);
            finish();
        }
    }
}