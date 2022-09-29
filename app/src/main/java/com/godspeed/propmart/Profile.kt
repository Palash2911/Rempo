package com.godspeed.propmart

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import com.godspeed.propmart.databinding.ActivityProfileBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.HashMap

class Profile : AppCompatActivity() {    private lateinit var binding: ActivityProfileBinding
    private val db = Firebase.firestore
    var num: String = ""
    lateinit var datepick: Button
    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_profile)

        binding = ActivityProfileBinding.inflate(layoutInflater)
//        datepick = findViewById(R.id.dobbtn2)
        setContentView(binding.root)

        num = intent.extras?.get("Number").toString()
        binding.phone.text = num

        val mDialog = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val myFormat = "MM/dd/yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            binding.dobbtn2.text = SimpleDateFormat("MM/dd/yyyy", Locale.US).format(cal.time)
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

        binding.dobbtn2.setOnClickListener {
            mDialog.show()
        }

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
        if(binding.dobbtn2.text.isEmpty()){
            binding.dobbtn2.error = "Date of Birth Required !!"
            return
        }
        profile["Name"] = binding.nameprofile.text.toString()
        profile["Phone"] = binding.phone.text.toString()
        profile["Aadhar"] = binding.aadharfield.text.toString()
        profile["Email"] = binding.Email.text.toString()
        profile["dob"] = binding.dobbtn2.text.toString()
        profile["profilePicture"] = "downloadurl"
        profile["Uid"] = Firebase.auth.uid.toString()
        profile["Account"] = "Buyer"
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
        if(!flag)
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
                        val intent = Intent(this, SelectType::class.java)
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

    fun openDatepicker(view: View) {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener {
                    view, year, monthOfYear, dayOfMonth ->
                datepick.text = year.toString() + monthOfYear.toString() + dayOfMonth.toString() }
    }
}