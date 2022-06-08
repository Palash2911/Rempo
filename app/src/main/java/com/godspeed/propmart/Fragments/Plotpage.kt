package com.godspeed.propmart.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.snap
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.godspeed.propmart.Bottomtab
import com.godspeed.propmart.R
import com.godspeed.propmart.databinding.FragmentBidsfragBinding
import com.godspeed.propmart.databinding.FragmentPlotpageBinding
import com.godspeed.propmart.ui.Bookmarks.BookmarksViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Plotpage : Fragment() {
    private var _binding: FragmentPlotpageBinding? = null
    private val db = Firebase.firestore
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlotpageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val layoutid = activity?.intent?.getStringExtra("Layout_Id")
        val plotid = activity?.intent?.getStringExtra("Plot_Id")
        db.collection("Layouts").document(layoutid.toString()).collection("plots").document(plotid.toString()).get().addOnSuccessListener { snapshot ->
            binding.bidheading.text = plotid.toString()
            binding.totarea.text = snapshot["totalarea"].toString()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}