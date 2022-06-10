package com.godspeed.propmart.ui.more

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.godspeed.propmart.Editprofile
import com.godspeed.propmart.MainActivity
import com.godspeed.propmart.databinding.FragmentMorefragmentBinding
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage


class Morefragment : Fragment() {
    private var _binding: FragmentMorefragmentBinding? = null
    private val auth = FirebaseAuth.getInstance()
    private val binding get() = _binding!!
    private val db = Firebase.firestore
    private var profileuri : Uri = Uri.parse("downloadUrl");
    private lateinit var storage: FirebaseStorage;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val moreViewModel =
            ViewModelProvider(this).get(MoreViewModel::class.java)

        _binding = FragmentMorefragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        db.collection("Users").document(Firebase.auth.currentUser?.uid.toString())
            .get().addOnSuccessListener { snapshot ->
                binding.namemoresetting.setText(snapshot["Name"] as String);
                binding.numbersetting.setText(snapshot["Phone"] as String);
                profileuri = Uri.parse(snapshot["profilePicture"].toString())
        }

        storage = FirebaseStorage.getInstance();
        val storageRef= storage.reference.child("Profile/" + auth.uid.toString())
        Log.d("Image", storageRef.toString())

        Glide.with(requireContext())
            .load(storageRef).into(binding.profileimg)

        _binding!!.logoutll.setOnClickListener {
            auth.signOut()
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        _binding!!.profileimg.setImageURI(profileuri)

        _binding!!.profileimg.setOnClickListener {
            val gallery = Intent();
            gallery.setType("image/*");
            gallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(gallery,25);
        }

        db.collection("Users").document(Firebase.auth.currentUser?.uid.toString()).get().addOnSuccessListener { snapshot ->
            binding.namemoresetting.setText(snapshot["Name"] as String);
            binding.numbersetting.setText(snapshot["Phone"] as String);
        }

        _binding!!.editprofilell.setOnClickListener{
            val intent = Intent(activity, Editprofile::class.java)
            startActivity(intent)
        }
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==25){
            profileuri = data?.data!!
            _binding!!.profileimg.setImageURI(data?.data!!);
            Log.d("Hello", profileuri.toString())
            val storageRef= FirebaseStorage.getInstance().reference.child("Profile/" + auth.uid.toString())
            storageRef.putFile(profileuri).addOnSuccessListener { task ->
                val profileimg:String = task.storage.downloadUrl.toString();
                Log.d("Hello2", profileimg.toString())
                db.collection("Users").document(Firebase.auth.currentUser?.uid.toString()).update("profilePicture", profileimg)
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "Profile Image Added Successully !!", Toast.LENGTH_SHORT).show()
                    }
            }.addOnFailureListener(OnFailureListener { e ->
                Log.d("Hello", e.message.toString())
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}