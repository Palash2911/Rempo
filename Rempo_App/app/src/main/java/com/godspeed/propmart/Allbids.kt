package com.godspeed.propmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Adapters.BidsAdapter
import com.godspeed.propmart.Adapters.allBidsAdapter
import com.godspeed.propmart.Models.Bidscardmodel
import com.godspeed.propmart.Models.allBidsModel
import com.godspeed.propmart.databinding.ActivityAllbidsBinding
import com.godspeed.propmart.databinding.ActivityEditPlotBinding
import com.godspeed.propmart.databinding.FragmentBidsfragBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Allbids : AppCompatActivity() {
    private var _binding: ActivityAllbidsBinding? = null
    private val db = Firebase.firestore
    private lateinit var adapter: allBidsAdapter;
    private lateinit var cards:MutableList<allBidsModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cards = ArrayList<allBidsModel>()
        adapter = allBidsAdapter(this, cards);
        _binding  = ActivityAllbidsBinding.inflate(layoutInflater);
        setContentView(_binding!!.root)
        _binding!!.bidsrecRv.layoutManager = LinearLayoutManager(this,
            RecyclerView.VERTICAL,false);
        _binding!!.bidsrecRv.adapter = adapter;

        val bundle :Bundle ?=intent.extras
        var layoutId = bundle?.getString("layoutId").toString();
        var plotId = bundle?.getString("plotId").toString();
        var plotno = ""

        if(layoutId=="Null")
        {
            db.collection("Plots").document(plotId).get().addOnSuccessListener {
                plotno = it["Plot No"].toString()
                db.collection("Plots").document(plotId).collection("bids").get()
                    .addOnSuccessListener { snapshot->
                        if(snapshot.isEmpty)
                        {
                            _binding!!.bidspro.visibility = GONE
                            _binding!!.allbidstv.visibility = VISIBLE
                            _binding!!.allbidstv.text = "NO BIDS YET !!"
                        }
                        else
                        {
                            for(ss in snapshot)
                            {
                                val name = ss["name"].toString()
                                val date = ss["time"].toString().substring(0, 11)
                                val bid = "Rs." + ss["placeBid"].toString()
                                val pno = "+91 " + ss["Phone"].toString()
                                val card: allBidsModel =
                                    allBidsModel(
                                        plotId, "Null", name, date, bid, pno
                                    );
                                cards.add(card)
                                if(cards.size>0)
                                {
                                    _binding!!.bidspro.visibility = GONE
                                    _binding!!.allbidstv.visibility = GONE
                                }
                            }
                        }
                    }.addOnFailureListener {
                        Toast.makeText(this, "Some Internal Error !!", Toast.LENGTH_SHORT).show()
                    }
            }.addOnFailureListener {
                Toast.makeText(this, "Some Internal Error !!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}