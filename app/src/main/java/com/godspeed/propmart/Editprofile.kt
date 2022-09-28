package com.godspeed.propmart

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.godspeed.propmart.databinding.ActivityEditprofileBinding
import com.godspeed.propmart.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class Editprofile : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private lateinit var binding: ActivityEditprofileBinding
    private val db = Firebase.firestore
    lateinit var datepick: Button
    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofile)

        binding = ActivityEditprofileBinding.inflate(layoutInflater)
        db.collection("Users").document(Firebase.auth.currentUser?.uid.toString()).get().addOnSuccessListener { snapshot ->
            binding.nameprofileedit.setText(snapshot["Name"] as String);
            binding.phoneedit.setText(snapshot["Phone"] as String);
            binding.Emailedit.setText(snapshot["Email"] as String);
            binding.dobbtn.setText(snapshot["dob"] as String);
        }
        datepick = findViewById(R.id.dobbtn)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar2)

        val mDialog = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val myFormat = "MM/dd/yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            binding.dobbtn.text = SimpleDateFormat("MM/dd/yyyy", Locale.US).format(cal.time)
        }, cal[Calendar.YEAR], cal[Calendar.MONTH], cal[Calendar.DAY_OF_MONTH])

        val minDay = 1
        val minMonth = 1
        val minYear = 1940
        cal.set(minYear, minMonth-1, minDay)
        mDialog.datePicker.minDate = cal.timeInMillis

        val maxDay = 31
        val maxMonth = 12
        val maxYear = 2004
        cal.set(maxYear, maxMonth-1, maxDay)
        mDialog.datePicker.maxDate = cal.timeInMillis
        binding.dobbtn.setOnClickListener {
            Log.d("btn", SimpleDateFormat("MM/dd/yyyy", Locale.US).format(cal.time).toString())
            mDialog.show()
        }

        supportActionBar!!.title = "Edit Profile"
        binding.toolbar2.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_arrow_back_24)
        binding.toolbar2.setNavigationOnClickListener {
            val intent = Intent(this, Bottomtab::class.java)
            startActivity(intent)
            finish()
        }
        binding.editprfbtn.setOnClickListener{
            editprf()
        }
    }

    private fun editprf()
    {
        val profile:HashMap<String, Any> = HashMap()
        if(binding.nameprofileedit.text.isEmpty()){
            binding.nameprofileedit.error = "Name Required !!"
            return
        }
        if(binding.phoneedit.text.isEmpty()){
            binding.phoneedit.error = "Phone Number Required !!"
            return
        }
        if(binding.Emailedit.text.isEmpty()){
            binding.Emailedit.error = "Email Required !!"
            return
        }
        if(binding.dobbtn.text.isEmpty()){
            binding.dobbtn.error = "Date of Birth Required !!"
            return
        }
        profile["Name"] = binding.nameprofileedit.text.toString()
        profile["Phone"] = binding.phoneedit.text.toString()
        val num = profile["Number"]
        profile["Aadhar"] = binding.aadharfield.text.toString()
        profile["Email"] = binding.Emailedit.text.toString()
        profile["dob"] = binding.dobbtn.text.toString()
        profile["profilePicture"] = "downloadurl"
        profile["Uid"] = Firebase.auth.uid.toString()
        profile["Verified"] = true

        db.collection("Users").document(Firebase.auth.currentUser?.uid.toString())
            .update(profile as Map<String, Any>).addOnCompleteListener{ task->
                if (task.isSuccessful){
                    val intent = Intent(this, Bottomtab::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d(TAG, "Error saving profile! ", task.exception)
                    Toast.makeText(applicationContext, "Something went wrong!!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}