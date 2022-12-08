package com.godspeed.propmart.Fragments

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Adapters.BidsAdapter
import com.godspeed.propmart.Adapters.BookmarkViewPagerAdapter
import com.godspeed.propmart.Adapters.PropertyCardAdapter
import com.godspeed.propmart.Models.Bidscardmodel
import com.godspeed.propmart.Models.PropertyCardModel
import com.godspeed.propmart.Models.sellerhomepageModel
import com.godspeed.propmart.R
import com.godspeed.propmart.databinding.FragmentBidsfragBinding
import com.godspeed.propmart.databinding.FragmentBookmarkBinding
import com.godspeed.propmart.databinding.FragmentPlotbuyerBinding
import com.godspeed.propmart.databinding.FragmentPropertyBuyerBinding
import com.godspeed.propmart.ui.Bookmarks.BookmarksViewModel
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class propertyBuyer : Fragment() {
    private var binding: FragmentPropertyBuyerBinding? = null
    private val db = Firebase.firestore
    private lateinit var adapter: PropertyCardAdapter;
    private lateinit var cards:MutableList<PropertyCardModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cards = ArrayList<PropertyCardModel>()
        adapter = PropertyCardAdapter(requireActivity(), cards);
        binding = FragmentPropertyBuyerBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        binding!!.propertyRv.layoutManager = LinearLayoutManager(requireContext(),
            RecyclerView.VERTICAL,false);
        binding!!.propertyRv.adapter = adapter
        binding!!.progressBar8.visibility = VISIBLE

        db.collection("Layouts").get().addOnSuccessListener{
            it.documents.iterator().forEach { documentSnapshot ->
//                val title:String = documentSnapshot.get("Area") as String;
                val seller:String = documentSnapshot.get("sellerName") as String;
                val totalplots:String = documentSnapshot.get("totalPlots").toString();
                val address:String = documentSnapshot.get("address").toString().substring(0, 15) + "...";
                val talkua:String = documentSnapshot.get("address").toString().substring(0, 15) + "...";
//                val longitude:String = documentSnapshot.get("longitude") as String;
//                val latitude:String = documentSnapshot.get("latitude") as String;
                val plotImage:String = documentSnapshot.get("soldPlots").toString();
                val card =
                    PropertyCardModel(documentSnapshot.id, "Null", "Layout At 35", seller, address,
                        plotImage, totalplots, "", "", talkua);
                cards.add(card);
                adapter.notifyDataSetChanged();
            }
            if(cards.size==0)
            {
                binding!!.progressBar8.visibility = GONE
                binding!!.propertytv.visibility = VISIBLE
                binding!!.propertytv.text = "No Properties to Show"
            }
            else
            {
                binding!!.progressBar8.visibility = GONE
                binding!!.propertytv.visibility = GONE
            }
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "Something Went Wrong !", Toast.LENGTH_SHORT).show()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}