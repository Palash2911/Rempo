package com.godspeed.propmart.ui.homepageSeller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Adapters.PropertyCardAdapter
import com.godspeed.propmart.Adapters.sellerHomepageAdapter
import com.godspeed.propmart.Models.PropertyCardModel
import com.godspeed.propmart.Models.sellerhomepageModel
import com.godspeed.propmart.databinding.FragmentHomeBinding
import com.godspeed.propmart.sellProp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private lateinit var adapter: sellerHomepageAdapter;
    private lateinit var cards:MutableList<sellerhomepageModel>
    private lateinit var firestore: FirebaseFirestore;
    private val auth = FirebaseAuth.getInstance()
    private var _binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = _binding!!.root

        cards = ArrayList<sellerhomepageModel>();
        adapter = sellerHomepageAdapter(requireActivity(),cards);
        firestore = FirebaseFirestore.getInstance();
        _binding!!.sellerRv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false);
        _binding!!.sellerRv.adapter = adapter;
        firestore.collection("Plots").get().addOnSuccessListener{
            it.documents.iterator().forEach { documentSnapshot ->
                if(auth.currentUser?.uid.toString().equals(documentSnapshot.get("Uid")))
                {
                    val title:String = documentSnapshot.get("Area") as String;
                    val seller:String = documentSnapshot.get("Owner Name") as String;
                    val plotnumber:Int = documentSnapshot.get("Plot No").toString().toInt();
                    val address:String = documentSnapshot.get("District") as String;
//                val longitude:String = documentSnapshot.get("longitude") as String;
//                val latitude:String = documentSnapshot.get("latitude") as String;
                    val plotImage:String = documentSnapshot.get("Taluka") as String;

                    val card:sellerhomepageModel =
                        sellerhomepageModel(documentSnapshot.id.toString(), title, seller, address,
                            plotImage, plotnumber, "", "");

                    cards.add(card);
                }
            }
            _binding!!.sellerProgress.visibility = GONE
            if(cards.isEmpty())
            {
                _binding!!.sellerLl.visibility = VISIBLE
            }
            else
            {
                _binding!!.sellerRv.visibility = VISIBLE
            }
            adapter.notifyDataSetChanged();
        }.addOnFailureListener {
            Log.d("Helo", "Empty")
        }

        _binding!!.addPropertyBtn.setOnClickListener{
            val intent = Intent(requireContext(), sellProp::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}