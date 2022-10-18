package com.godspeed.propmart.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Adapters.PropertyCardAdapter
import com.godspeed.propmart.Models.PropertyCardModel
import com.godspeed.propmart.databinding.FragmentSavedfragBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Savedfrag : Fragment() {
    private var _binding: FragmentSavedfragBinding? = null
    private lateinit var adapter: PropertyCardAdapter;
    private lateinit var cards:MutableList<PropertyCardModel>

    private val binding get() = _binding!!
    private val db = Firebase.firestore
    lateinit var savelist: MutableList<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedfragBinding.inflate(inflater, container, false)
        val root: View = binding.root
        cards = ArrayList<PropertyCardModel>();
        adapter = PropertyCardAdapter(requireActivity(),cards);

        binding.saverv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.saverv.adapter = adapter

        Log.d("Saved", Firebase.auth.currentUser.toString())
        db.collection("Users").document(Firebase.auth.currentUser?.uid.toString())
            .collection("saved")
            .get().addOnSuccessListener{ snapshot->
                for(users in snapshot)
                {
                    Log.d("Layout", users["layoutId"].toString())
                    val title = users["title"].toString()
                    val seller = users["seller"].toString()
                    val totalPlots = users["totalPlots"].toString().toLong()
                    val address = users["address"].toString()
                    val longitude:String = users["longitude"] as String;
                    val latitude:String = users["latitude"] as String;

                    val card:PropertyCardModel =
                        PropertyCardModel(users.id,
                            title,seller,address,"",totalPlots,latitude,longitude);

                    cards.add(card)
                    Log.d("Lays", cards.size.toString())
                }
                if(cards.size>0)
                {
                    binding.progressBar.visibility = GONE
                    binding.savetext.visibility = GONE
                }
                else
                {
                    binding.progressBar.visibility = GONE
                    binding.savetext.text = "No Properties Saved Yet !!"
                }
                adapter.notifyDataSetChanged();
            }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}