package com.godspeed.propmart.ui.homepageSeller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.LOG
import com.godspeed.propmart.Adapters.PropertyCardAdapter
import com.godspeed.propmart.Adapters.sellerHomepageAdapter
import com.godspeed.propmart.ChooseActivity
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
                if(auth.currentUser?.uid.toString() == documentSnapshot.get("Uid"))
                {
                    val title:String = documentSnapshot.get("Area") as String;
                    val seller:String = documentSnapshot.get("Owner Name") as String;
                    val plotnumber:String = documentSnapshot.get("Plot No").toString();
                    val address:String = documentSnapshot.get("District") as String;
//                val longitude:String = documentSnapshot.get("longitude") as String;
//                val latitude:String = documentSnapshot.get("latitude") as String;
                    val plotImage:String = documentSnapshot.get("Taluka") as String;
                    val card:sellerhomepageModel =
                        sellerhomepageModel(documentSnapshot.id,"Null", title, seller, address,
                            plotImage, plotnumber, "", "");

                    cards.add(card);
                }
            }
            _binding!!.sellerProgress.visibility = GONE
            if(cards.isEmpty())
            {
                _binding!!.sellerLl.visibility = VISIBLE
                _binding!!.sellerRL.visibility = GONE
            }
            else
            {
                _binding!!.sellerLl.visibility = GONE
                _binding!!.sellerRL.visibility = VISIBLE
            }

            adapter.notifyDataSetChanged();
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "ERROR OCCURRED !", Toast.LENGTH_SHORT).show()
        }

        _binding!!.addPropertyBtn.setOnClickListener{
            val intent = Intent(requireContext(), ChooseActivity::class.java)
            startActivity(intent)
        }

        _binding!!.listPlotbtn.setOnClickListener{
            val intent = Intent(requireContext(), ChooseActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}