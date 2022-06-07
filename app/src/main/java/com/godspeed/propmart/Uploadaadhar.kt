package com.godspeed.propmart

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.godspeed.propmart.databinding.ActivityUploadaadharBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class Uploadaadhar : AppCompatActivity() {

    private lateinit var binding:ActivityUploadaadharBinding;
    private lateinit var aadharFrontUri :Uri;
    private lateinit var aadharBackUri:Uri;
    private lateinit var storage:FirebaseStorage;
    private lateinit var auth:FirebaseAuth;
    private lateinit var firestore: FirebaseFirestore;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadaadharBinding.inflate(layoutInflater);
        aadharFrontUri = Uri.EMPTY;
        aadharBackUri = Uri.EMPTY;
        setContentView(binding.root);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        binding.aadharFront.setOnClickListener {
            val gallery = Intent();
            gallery.setType("image/*");
            gallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(gallery,25);
        }

        binding.aadharBack.setOnClickListener{
            val gallery = Intent();
            gallery.setType("image/*");
            gallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(gallery,35);
        }

        binding.proceedBtn.setOnClickListener{
            if(binding.aadharNumber.text.isNullOrEmpty() || binding.aadharNumber.text.length!=12){
                binding.aadharNumber.setError("Please Enter an Valid Aadhar Number");
            }
            else if(aadharFrontUri==Uri.EMPTY || aadharBackUri== Uri.EMPTY){
                Toast.makeText(this,"Upload Aadhar Pictures",Toast.LENGTH_LONG).show()
            }
           else{
                    val aadharNum = binding.aadharNumber.text.toString();
                    val storageRef:StorageReference = storage.reference.child("AadharImages").child(auth.uid.toString());
                    storageRef.child("Front").putFile(aadharFrontUri).addOnCompleteListener{ task->
                          if(task.isSuccessful){
                           val frontAadhar:String = storageRef.child("Front").downloadUrl.toString();
                              storageRef.child("Back").putFile(aadharBackUri).addOnCompleteListener{task ->
                                  if(task.isSuccessful){
                                      val backAadhar:String = storageRef.child("Back").downloadUrl.toString();
                                      val map:Map<String,String> = mapOf("frontAadhar" to frontAadhar ,"backAadhar" to backAadhar);
                                      firestore.collection("Users").document(auth.uid!!)
                                          .update(map).addOnSuccessListener {

                                              sendOtp(aadharNum)

                                          }
                                  }
                              }
                          }
                    }

            }
        }


    }

    private fun sendOtp(aadharNum:String) {
        val intent:Intent = Intent(this,Aadharotp::class.java);
        intent.putExtra("aadharNumber",aadharNum);
        startActivity(intent);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==25){
            aadharFrontUri = data?.data!!
            binding.aadharFront.setImageURI(data?.data!!);
        }

        if(requestCode==35){
            aadharBackUri = data?.data!!
            binding.aadharBack.setImageURI(data?.data!!);
        }


    }
}