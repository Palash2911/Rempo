package com.godspeed.propmart.ui.Hompage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.godspeed.propmart.Adapters.PropertyCardAdapter
import com.godspeed.propmart.Fragments.plotBuyer
import com.godspeed.propmart.Fragments.propertyBuyer
import com.godspeed.propmart.Models.PropertyCardModel
import com.godspeed.propmart.R
import com.godspeed.propmart.databinding.FragmentHomepageBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
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
        _binding = FragmentHomepageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        addFragment(propertyBuyer())

        _binding!!.tabLayoutprop.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                setFragment(tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        _binding!!.filterImg.setOnClickListener{
            val bottomSheet = BottomSheetDialog(activity!!)
            val view = layoutInflater.inflate(R.layout.bottomsort, null)
            val agriculturalBtn = view.findViewById<RadioButton>(R.id.agriculturalSort)
            val commercialBtn = view.findViewById<RadioButton>(R.id.commercialSort)
            val residentialBtn = view.findViewById<RadioButton>(R.id.residentialSort)

            residentialBtn.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.setCancelable(false)
            bottomSheet.setContentView(view)
            // on below line we are calling
            // a show method to display a dialog.
            bottomSheet.show()
        }

//        cards = ArrayList<PropertyCardModel>();
//        adapter = PropertyCardAdapter(requireActivity(),cards);
//        firestore = FirebaseFirestore.getInstance();
//        binding.layoutRecyclerView.layoutManager = LinearLayoutManager(requireContext(),
//        RecyclerView.VERTICAL,false);
//        binding.layoutRecyclerView.adapter = adapter;
//
//        firestore.collection("Layouts").get().addOnSuccessListener{
//            it.documents.iterator().forEach { documentSnapshot ->
//                val title:String = documentSnapshot.get("title") as String;
//                val seller:String = documentSnapshot.get("sellerName") as String;
//                val totalPlots:Long = documentSnapshot.get("totalPlots") as Long;
//                val address:String = documentSnapshot.get("address") as String;
//                val longitude:String = documentSnapshot.get("longitude") as String;
//                val latitude:String = documentSnapshot.get("latitude") as String;
//
//               val card:PropertyCardModel =
//                   PropertyCardModel(documentSnapshot.id.toString(),
//                       title,seller,address,"",totalPlots,latitude,longitude);
//
//                cards.add(card);
//            }
//            adapter.notifyDataSetChanged();
//        }
//
////        binding.imageButton.setOnClickListener{
////            requireActivity().finish();
////        }

        return root
    }

    private fun setFragment(position: Int) {
        when (position + 1) {
            1 -> {
                _binding?.filterImg?.visibility = GONE
                addFragment(propertyBuyer())
            }
            2 -> {
                _binding?.filterImg?.visibility = VISIBLE
                addFragment(plotBuyer())
            }
        }
    }

    private fun addFragment(fragment: Fragment) {
        val manager: FragmentManager = requireActivity().supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.propFL, fragment)
        transaction.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}