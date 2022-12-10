package com.godspeed.propmart

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.animation.core.snap
import androidx.core.content.ContextCompat
import com.godspeed.propmart.databinding.ActivityEditprofileBinding
import com.godspeed.propmart.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class Editprofile : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private lateinit var binding: ActivityEditprofileBinding
    private val db = Firebase.firestore
    lateinit var datepick: Button
    var cal = Calendar.getInstance()
    val profile:HashMap<String, Any> = HashMap()
    var doc=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofile)
        binding = ActivityEditprofileBinding.inflate(layoutInflater)
        db.collection("Users").document(Firebase.auth.currentUser?.uid.toString()).get().addOnSuccessListener { snapshot ->
            binding.nameprofileedit.setText(snapshot["Name"] as String);
            binding.phoneedit.setText(snapshot["Phone"] as String);
            binding.Emailedit.setText(snapshot["Email"] as String);
            binding.dobbtn.text = snapshot["dob"] as String;
            binding.aadharfield.text = snapshot["Aadhar_No"].toString()
            if(snapshot["ID"].toString().isEmpty())
            {
                binding.uploadbtnaadhar.visibility = View.VISIBLE
                binding.deletebtnaadhar.visibility = View.GONE
            }
        }
        binding.deletebtnaadhar.setOnClickListener {
            binding.deletebtnaadhar.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            val storageref = FirebaseStorage.getInstance().getReference("ID_Proof/" + auth.currentUser?.uid.toString() + "_ID")
            storageref.delete().addOnSuccessListener {
                profile["ID"]=""
                Toast.makeText(this,"Deleted", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
                binding.uploadbtnaadhar.visibility = View.VISIBLE
                doc=0
            }.addOnFailureListener {
                Toast.makeText(this,"Something Went Wrong", Toast.LENGTH_SHORT).show()
                binding.deletebtnaadhar.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }
        binding.uploadbtnaadhar.setOnClickListener {
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 1);
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
        if(doc==0)
        {
            Toast.makeText(this, "Please Upload ID Proof", Toast.LENGTH_SHORT).show()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1 && resultCode == RESULT_OK)
        {
            binding.uploadbtnaadhar.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            val imageUriRaw = data?.extras?.get("data") as Bitmap
            val bytes = ByteArrayOutputStream()
            val path = MediaStore.Images.Media.insertImage(this.contentResolver, imageUriRaw, "Title", null)
            val imageUriToUpload = Uri.parse(path.toString())
            val storageref = FirebaseStorage.getInstance().getReference("ID_Proof/" + auth.currentUser?.uid.toString() + "_ID")
            storageref.putFile(imageUriToUpload).addOnSuccessListener {
                storageref.downloadUrl.addOnSuccessListener{ uri ->
                    val downloadUrl = uri.toString();
                    val docs:HashMap<String, Any> = HashMap()
                    profile["ID"] = downloadUrl
                    doc = 1
                    Toast.makeText(this , "File Uploaded Successfully", Toast.LENGTH_SHORT).show()
                    binding.deletebtnaadhar.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }.addOnFailureListener {
                    Log.d("Inner Error", it.toString())
                }
            }.addOnFailureListener{
                Log.d("Outer Error", it.toString())
            }
        }
    }

}