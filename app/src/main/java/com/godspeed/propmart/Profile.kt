package com.godspeed.propmart

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.godspeed.propmart.databinding.ActivityProfileBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime

class Profile : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val db = Firebase.firestore
    var num: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        binding = ActivityProfileBinding.inflate(layoutInflater)

        setContentView(binding.root)

        num = intent.extras?.get("Number").toString()
        binding.phone.text = num

        binding.saveprofile.setOnClickListener {
            submitProf()
        }
    }

    private fun submitProf() {
        val profile:HashMap<String, Any> = HashMap()
        if(binding.nameprofile.text.isEmpty()){
            binding.nameprofile.error = "Name Required !!"
            return
        }
        if(binding.Email.text.isEmpty()){
            binding.Email.error = "Email Required !!"
            return
        }
        if(binding.dob.text.isEmpty()){
            binding.dob.error = "Date of Birth Required !!"
            return
        }
        profile["Name"] = binding.nameprofile.text.toString()
        profile["Phone"] = binding.phone.text.toString()
        val num = profile["Number"]
        profile["Aadhar"] = binding.aadharfield.text.toString()
        profile["Email"] = binding.Email.text.toString()
        profile["dob"] = binding.dob.text.toString()
        profile["profilePicture"] = "downloadurl"
        profile["Uid"] = Firebase.auth.uid.toString()
        profile["Verified"] = true
        var flag = true
        var uid = ""
        db.collection("Users").get().addOnSuccessListener { snapsnot->
            for(users in snapsnot)
            {
                Log.d(TAG, users["Phone"].toString() )
                if(profile["Phone"] == users["Phone"].toString())
                {
                    flag=false;
                    uid = users["uid"].toString()
                    break;
                }
            }
        }
        if(flag==false)
        {
            db.collection("Users").document(uid)
                .set(profile).addOnCompleteListener{task->
                    if (task.isSuccessful){
                        val intent = Intent(this, Profileverify::class.java)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this, "Welcome Champion !! ", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.d(TAG, "Error saving profile! ", task.exception)
                        Toast.makeText(applicationContext, "Something went wrong!!", Toast.LENGTH_SHORT).show()
                    }
                }
            db.collection("Users").document(uid).update(uid, Firebase.auth.currentUser.toString())
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
        }
        else
        {
            db.collection("Users").document(Firebase.auth.currentUser?.uid.toString())
                .set(profile).addOnCompleteListener{task->
                    if (task.isSuccessful){
                        val intent = Intent(this, Profileverify::class.java)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this, "Welcome Champion !! ", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.d(TAG, "Error saving profile! ", task.exception)
                        Toast.makeText(applicationContext, "Something went wrong!!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}