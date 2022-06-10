package com.godspeed.propmart.ui.Hompage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Adapters.PropertyCardAdapter
import com.godspeed.propmart.Models.PropertyCardModel
import com.godspeed.propmart.databinding.FragmentHomepageBinding
import com.google.firebase.firestore.FirebaseFirestore


class HompageFragment : Fragment() {
    private lateinit var adapter: PropertyCardAdapter;
    private lateinit var cards:MutableList<PropertyCardModel>
    private lateinit var firestore: FirebaseFirestore;



    private var _binding: FragmentHomepageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val HompageViewModel =
            ViewModelProvider(this).get(HompageViewModel::class.java)

        cards = ArrayList<PropertyCardModel>();
        adapter = PropertyCardAdapter(requireActivity(),cards);
        firestore = FirebaseFirestore.getInstance();
        _binding = FragmentHomepageBinding.inflate(inflater, container, false)

        val root: View = binding.root

        binding.layoutRecyclerView.layoutManager = LinearLayoutManager(requireContext(),
        RecyclerView.VERTICAL,false);
        binding.layoutRecyclerView.adapter = adapter;

        firestore.collection("Layouts").get().addOnSuccessListener{
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
        }

        binding.imageButton.setOnClickListener{
            requireActivity().finish();
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}