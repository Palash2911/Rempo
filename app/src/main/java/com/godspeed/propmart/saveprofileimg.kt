package com.godspeed.propmart

import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.godspeed.propmart.databinding.ActivitySaveprofileimgBinding
import com.godspeed.propmart.databinding.FragmentMorefragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class saveprofileimg : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private var profileuri : Uri = Uri.parse("downloadUrl");
    private lateinit var storage: FirebaseStorage;
    lateinit var profileimg: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saveprofileimg)

        val profilebtn = findViewById<Button>(R.id.saveprobtn)
        var profileimg = findViewById<ImageView>(R.id.profileimg)

        profileimg.setOnClickListener {

        }

        profilebtn.setOnClickListener{
        }
    }



}