package com.godspeed.propmart

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.godspeed.propmart.databinding.ActivityUploadaadharBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class Uploadaadhar : AppCompatActivity() {

    private lateinit var binding:ActivityUploadaadharBinding;
    private lateinit var aadharFrontUri :Uri;
    private lateinit var storage:FirebaseStorage;
    private lateinit var auth:FirebaseAuth;
    private lateinit var firestore: FirebaseFirestore;
    var aadhar:HashMap<String, Any> = HashMap()
    var doc = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadaadharBinding.inflate(layoutInflater);
        setContentView(binding.root);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();


        val prbtn = findViewById<Button>(R.id.proceedBtn)
        binding.uploadbtnaadhar.setOnClickListener {
            var intent = Intent()
            intent.type = "application/pdf"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 1);
        }

        binding.deletebtnaadhar.setOnClickListener {
            binding.deletebtnaadhar.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            val storageref = FirebaseStorage.getInstance().getReference("Documents/" + auth.currentUser?.uid.toString() + "_ID")
            storageref.delete().addOnSuccessListener {
                aadhar["ID"]=""
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

        prbtn.setOnClickListener{
            Log.d("Aadhar", "clicked")
            if(binding.aadharfield.toString().isEmpty())
            {
                binding.aadharfield.error = "Aadhar Number Required !"
            }
            else
            {
                if(doc==1)
                {
                    aadhar["Aadhar_No"] = binding.aadharfield.text.toString()
                    firestore.collection("Users").document(Firebase.auth.currentUser?.uid.toString())
                        .update(aadhar).addOnCompleteListener{task->
                            if (task.isSuccessful){
                                val intent = Intent(this, SelectType::class.java)
                                startActivity(intent)
                                finish()
                                Toast.makeText(this, "Profile Created Successfully !! ", Toast.LENGTH_SHORT).show()
                            } else {
                                Log.d(ContentValues.TAG, "Error saving profile! ", task.exception)
                                Toast.makeText(applicationContext, "Something went wrong!!", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
                else
                {
                    Toast.makeText(this, "Please Upload Identity Proof !! ", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1 && resultCode == RESULT_OK)
        {
            binding.uploadbtnaadhar.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            val storageref = FirebaseStorage.getInstance().getReference("Documents/" + auth.currentUser?.uid.toString() + "_ID")
            storageref.putFile(data?.data!!).addOnSuccessListener {
                storageref.downloadUrl.addOnSuccessListener{ uri ->
                    val downloadUrl = uri.toString();
                    val docs:HashMap<String, Any> = HashMap()
                    aadhar["ID"] = downloadUrl
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