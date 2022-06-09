package com.godspeed.propmart.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Adapters.BidsAdapter
import com.godspeed.propmart.Adapters.BookmarkViewPagerAdapter
import com.godspeed.propmart.Adapters.PropertyCardAdapter
import com.godspeed.propmart.Models.Bidscardmodel
import com.godspeed.propmart.Models.PropertyCardModel
import com.godspeed.propmart.R
import com.godspeed.propmart.databinding.FragmentBidsfragBinding
import com.godspeed.propmart.databinding.FragmentBookmarkBinding
import com.godspeed.propmart.ui.Bookmarks.BookmarksViewModel
import com.google.android.material.tabs.TabLayout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Bidsfrag : Fragment() {
    private var _binding: FragmentBidsfragBinding? = null
    private val db = Firebase.firestore
    private val binding get() = _binding!!
    private lateinit var adapter: BidsAdapter;
    private lateinit var cards:MutableList<Bidscardmodel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        cards = ArrayList<Bidscardmodel>()
        adapter = BidsAdapter(requireActivity(), cards);
        _binding = FragmentBidsfragBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.bidsrv.layoutManager = LinearLayoutManager(requireContext(),
            RecyclerView.VERTICAL,false);
        binding.bidsrv.adapter = adapter;

        db.collection("Users").document("sample_uid").collection("bids")
            .get().addOnSuccessListener { snapshot->
                for(users in snapshot)
                {
                    Log.d("Layout", users["layoutId"].toString())
//                    savelist.add(users["layoutId"].toString())
                    db.collection("Layouts").document(users["layoutId"] as String).get().addOnSuccessListener { snapshots->
                            val title = snapshots["title"].toString()
                            val seller = snapshots["sellerName"].toString()
                            val bidamt = users["bidAmount"].toString()
                            val plotno = users["plotNo"].toString()

                            val card:Bidscardmodel =
                                Bidscardmodel(snapshots.id.toString(),
                                    bidamt,seller,"",plotno,title);

                            cards.add(card)
                            Log.d("Bid time", cards.toString())
                            if(cards.size>0)
                            {
                                binding.bidstext.visibility = View.GONE
                            }
                            else
                            {
                                binding.bidstext.text = "No Properties with Bids Yet !!"
                            }
                        adapter.notifyDataSetChanged();
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