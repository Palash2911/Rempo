package com.godspeed.propmart.ui.Bookmarks

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.godspeed.propmart.Bottomtab
import com.godspeed.propmart.databinding.FragmentBookmarkBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BookmarksFragment : Fragment() {

    private val db = Firebase.firestore
    private var _binding: FragmentBookmarkBinding? = null
    lateinit var bookmarklist: MutableList<Int>
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val BookmarksViewModel =
            ViewModelProvider(this).get(BookmarksViewModel::class.java)

        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // change sample_uid to auth_id
//        db.collection("Users").document("sample_uid")
//            .collection("saved").get().addOnSuccessListener { snapshot ->
//                for (layout in snapshot) {
//                    bookmarklist.add(Integer.parseInt(layout["layout_Id"].toString()))
//                }
//            }
//        db.collection("Layouts").get().addOnSuccessListener { snapshot ->
//            for(layout in snapshot)
//            {
//                if(bookmarklist.get(0) == layout["layout_Id"])
//                {
//                    add to list
//                }
//            }
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}