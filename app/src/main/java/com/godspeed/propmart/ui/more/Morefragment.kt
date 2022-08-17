package com.godspeed.propmart.ui.more

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.godspeed.propmart.*
import com.godspeed.propmart.databinding.FragmentMorefragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
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
//        val storageRef= storage.reference.child("Profile/" + auth.uid.toString())
//        storageRef.downloadUrl.addOnSuccessListener {
////            Glide.with(requireContext())
////                .load(storageRef).into(binding.profileimg)
//        }
//        Log.d("Image", storageRef.toString())

        _binding!!.accType.setOnClickListener{
            val bottomSheet = BottomSheetDialog(activity!!)
            val view = layoutInflater.inflate(R.layout.bottomacctype, null)
            val buyerAcc = view.findViewById<RadioButton>(R.id.buyerAcc)
            val sellerAcc = view.findViewById<RadioButton>(R.id.sellerAc)

            sellerAcc.setOnClickListener {
                val intent = Intent(activity, BottomnavSeller::class.java)
                startActivity(intent)
                activity?.finish()
                bottomSheet.dismiss()
            }
            buyerAcc.setOnClickListener{
                val intent = Intent(activity, Bottomtab::class.java)
                startActivity(intent)
                activity?.finish()
                bottomSheet.dismiss()
            }
            bottomSheet.setCancelable(false)
            bottomSheet.setContentView(view)
            bottomSheet.show()
        }

        _binding!!.logoutll.setOnClickListener {
            auth.signOut()
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

//        _binding!!.profileimg.setImageURI(profileuri)
//
//        _binding!!.profileimg.setOnClickListener {
//            val gallery = Intent();
//            gallery.setType("image/*");
//            gallery.setAction(Intent.ACTION_GET_CONTENT);
//            startActivityForResult(gallery,25);
//        }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}