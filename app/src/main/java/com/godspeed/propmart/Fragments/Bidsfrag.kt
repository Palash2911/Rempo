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
import com.godspeed.propmart.R
import com.godspeed.propmart.databinding.FragmentBidsfragBinding
import com.godspeed.propmart.databinding.FragmentBookmarkBinding
import com.godspeed.propmart.ui.Bookmarks.BookmarksViewModel
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.ktx.auth
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

        db.collection("Users").document(Firebase.auth.currentUser?.uid.toString())
            .collection("bids").get().addOnSuccessListener { snapshot->
                for(users in snapshot)
                {
                    Log.d("Layout", users["layoutId"].toString())
                    db.collection("Layouts")
                        .document(users["layoutId"] as String).get().addOnSuccessListener { snapshots ->
                        val title = snapshots["title"].toString()
                        val seller = snapshots["sellerName"].toString()
                        val bidamt = users["bidAmount"].toString()
                        val plotno = users["plotNo"].toString()

                        val card: Bidscardmodel =
                            Bidscardmodel(
                                snapshots.id.toString(),
                                bidamt, seller, "", plotno, title
                            );

                        cards.add(card)
                            if(cards.size>0)
                            {
                                Log.d("Bid time", cards.size.toString())
                                binding.progressBar2.visibility = GONE
                                binding.bidstext.visibility = GONE
                            }
                            else
                            {
                                Log.d("Bid time", cards.size.toString())
                                binding.progressBar2.visibility = GONE
                                binding.bidstext.visibility = VISIBLE
                                binding.bidstext.text = "No Properties with Bids Yet !!"
                            }
                        adapter.notifyDataSetChanged();
                    }.addOnFailureListener {
                            Toast.makeText(requireContext(), "No Bid Found", Toast.LENGTH_SHORT).show()
                        }
                }
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "No Bid Found3", Toast.LENGTH_SHORT).show()
            }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}