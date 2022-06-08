package com.godspeed.propmart.Fragments

import android.content.ContentValues
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.snap
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.godspeed.propmart.Bidplaced
import com.godspeed.propmart.Bottomtab
import com.godspeed.propmart.Editprofile
import com.godspeed.propmart.R
import com.godspeed.propmart.databinding.FragmentBidsfragBinding
import com.godspeed.propmart.databinding.FragmentPlotpageBinding
import com.godspeed.propmart.ui.Bookmarks.BookmarksViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class Plotpage : Fragment() {
    private var _binding: FragmentPlotpageBinding? = null
    private val db = Firebase.firestore
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlotpageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val layoutid = activity?.intent?.getStringExtra("Layout_Id")
        val plotid = activity?.intent?.getStringExtra("Plot_Id")
        db.collection("Layouts").document("sample_layout").collection("plots").document("plot1").get().addOnSuccessListener { snapshot ->
            binding.bidheading.text = plotid.toString()
            binding.totarea.text = "Total Area : " + snapshot["totalArea"].toString() + "Sq.m"
            binding.descplot.text = snapshot["description"].toString()
            binding.rateplot.text = "Rate : " + snapshot["rate"].toString() + " Rs./sq.m"
            binding.dimension.text = snapshot["dimension"].toString()
            binding.warningplot.text = "Current rate at this site is" + snapshot["rate"].toString() + "₹/sq.m. Kindly place your bid by considering current property rate"
        }

        binding.placebidbtn.setOnClickListener {
            if(binding.Bidamount.text.isEmpty()|| (binding.Bidamount.text.toString()>="0" && binding.Bidamount.text.toString()<="100"))
            {
                Toast.makeText(requireContext(), "Enter Valid Bid", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val profile:HashMap<String, Any> = HashMap()
                db.collection("Users").document(Firebase.auth.currentUser?.uid.toString())
                    .get().addOnSuccessListener{snapshot ->
                        profile["name"]=snapshot["Name"].toString()
                        profile["placeBid"]=binding.Bidamount.text.toString()
                        val current = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))
                        profile["time"]=current
                        profile["uid"]=Firebase.auth.currentUser?.uid.toString()
                        db.collection("Layouts").document("sample_layout").collection("plots").document("plot1").collection("bids").document(Firebase.auth.currentUser?.uid.toString()).set(profile).addOnCompleteListener { task->
                            if(task.isSuccessful)
                            {
                                val intent = Intent(requireContext(), Bidplaced::class.java)
                                startActivity(intent)
                            }
                        }
                    }
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}