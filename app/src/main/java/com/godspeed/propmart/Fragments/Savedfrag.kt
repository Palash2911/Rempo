package com.godspeed.propmart.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Adapters.PropertyCardAdapter
import com.godspeed.propmart.Models.PropertyCardModel
import com.godspeed.propmart.databinding.FragmentSavedfragBinding
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

        db.collection("Users").document("sample_uid").collection("saved")
            .get().addOnSuccessListener { snapshot->
                for(users in snapshot)
                {
                    Log.d("Layout", users["layoutId"].toString())
//                    savelist.add(users["layoutId"].toString())
                }
            }
        db.collection("Layouts").get().addOnSuccessListener { snapshot->
            for(layouts in snapshot)
            {
                val title = layouts["title"].toString()
                val seller = layouts["sellerName"].toString()
                val totalPlots = layouts["totalPlots"].toString()
                val address = layouts["address"].toString()

                val card:PropertyCardModel =
                    PropertyCardModel(layouts.id.toString(),
                        title,seller,address,"",totalPlots.toLong());

                cards.add(card)
                Log.d("Lays", cards.size.toString())
                if(cards.size>0)
                {
                    binding.savetext.visibility = GONE
                }
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