package com.godspeed.propmart.ui.Location

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.godspeed.propmart.Adapters.PropertyCardAdapter
import com.godspeed.propmart.Models.PropertyCardModel
import com.godspeed.propmart.databinding.FragmentLocationBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase

class LocationFragment : Fragment() {

    private var _binding: FragmentLocationBinding? = null
    private lateinit var adapter: PropertyCardAdapter;
    private lateinit var cards:MutableList<PropertyCardModel>
    private lateinit var firestore: FirebaseFirestore;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val LocationViewModel =
            ViewModelProvider(this).get(LocationViewModel::class.java)

        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        cards = ArrayList<PropertyCardModel>();
        adapter = PropertyCardAdapter(requireActivity(),cards);
        firestore = FirebaseFirestore.getInstance();

        val pDailog = ProgressDialog(requireActivity());
        pDailog.setCancelable(false);
        pDailog.setMessage("Please Wait");

        firestore.collection("Users").document(Firebase.auth.currentUser?.uid.toString())
            .get().addOnSuccessListener { snapshot->
                binding.locationnameuser.text = snapshot["Name"].toString()
        }
        val profilePic = ""

        Glide.with(requireActivity())
            .load(profilePic).into(binding.profileImage);


        binding.locationRv.layoutManager = LinearLayoutManager(requireContext(),
            RecyclerView.VERTICAL,false);
        binding.locationRv.adapter = adapter;


        binding.explore.setOnClickListener{
                pDailog.show();
                var query:Query = firestore.collection("Layouts");
                if(binding.state.text.isNotBlank() && binding.district.text.isNotBlank() && binding.taluka.text.isNotBlank()){
                    query = firestore.collection("Layouts")
                        .whereEqualTo("state",binding.state.text.toString())
                        .whereEqualTo("district",binding.district.text.toString())
                        .whereEqualTo("taluka",binding.taluka.text.toString());
                }
                else if(binding.state.text.isNotBlank()){
                    query = firestore.collection("Layouts")
                        .whereEqualTo("state",binding.state.text.toString())

                }
                else if(binding.state.text.isNotBlank() &&  binding.district.text.isNotBlank()){
                    query = firestore.collection("Layouts")
                        .whereEqualTo("state",binding.state.text.toString())
                        .whereEqualTo("district",binding.district.text.toString())
                }

                query.get().addOnSuccessListener {
                    it.documents.iterator().forEach { documentSnapshot ->
                        val title:String = documentSnapshot.get("title") as String;
                        val seller:String = documentSnapshot.get("sellerName") as String;
                        val totalPlots:Long = documentSnapshot.get("totalPlots") as Long;
                        val address:String = documentSnapshot.get("address") as String;
                        val longitude:String = documentSnapshot.get("longitude") as String;
                        val latitude:String = documentSnapshot.get("latitude") as String;

                        val card:PropertyCardModel =
                            PropertyCardModel(documentSnapshot.id.toString(),
                                title,seller,address,"",totalPlots,latitude,longitude);

                        cards.add(card);
                    }

                    adapter.notifyDataSetChanged();
                    binding.locationRv.visibility = View.VISIBLE;
                    binding.searchLayout.visibility = View.GONE;
                    binding.explore.visibility = View.GONE;
                    binding.toolbar.visibility = View.VISIBLE;

                    pDailog.dismiss();
                }



        }

        binding.backButton.setOnClickListener{
            cards.clear();
            binding.locationRv.visibility = View.GONE;
            binding.searchLayout.visibility = View.VISIBLE;
            binding.toolbar.visibility = View.GONE;

        }





        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}