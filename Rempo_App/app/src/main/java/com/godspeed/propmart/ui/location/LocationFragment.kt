package com.godspeed.propmart.ui.Location

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.godspeed.propmart.Adapters.PropertyCardAdapter
import com.godspeed.propmart.Models.PropertyCardModel
import com.godspeed.propmart.R
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
    private lateinit var autoState:AutoCompleteTextView;

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
        autoState = binding.state;

        val pDailog = ProgressDialog(requireActivity());
        pDailog.setCancelable(false);
        pDailog.setMessage("Please Wait");


        val profilePic = ""

        Glide.with(requireActivity())
            .load(profilePic).into(binding.profileImage);


        binding.locationRv.layoutManager = LinearLayoutManager(requireContext(),
            RecyclerView.VERTICAL,false);
        binding.locationRv.adapter = adapter;

        //AutoComplete State and District TextView
        // -------------------------------------------------------------------------------------------->

        val stateArray = resources.getStringArray(R.array.array_indian_states)



        val stateAdapter: ArrayAdapter<String> = ArrayAdapter(requireActivity(),
            android.R.layout.simple_list_item_1,stateArray);
        autoState.setAdapter(stateAdapter);
        autoState.threshold = 1;

        autoState.onItemClickListener = AdapterView
            .OnItemClickListener{
                    parent,view,position,id->
                val selectedItem = parent.getItemAtPosition(position)
                    .toString()
                when(selectedItem){

                    "Andhra Pradesh" ->{

                        val array = resources.getStringArray(R.array.array_andhra_pradesh_districts);
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(
                            requireActivity(), android.R.layout.simple_list_item_1,
                            array
                        );
                        binding.district.setAdapter(districtAdapter)}

                    "Arunachal Pradesh"-> {

                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(
                            requireActivity(), android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_arunachal_pradesh_districts)
                        );
                        binding.district.setAdapter(districtAdapter)
                    }

                    "Assam"->{
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_assam_districts));
                        binding.district.setAdapter(districtAdapter)}

                    "Bihar"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_bihar_districts));
                        binding.district.setAdapter(districtAdapter)}

                    "Chhattisgarh"-> {
                        val array = resources.getStringArray(R.array.array_chhattisgarh_districts);
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(
                            requireActivity(), android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_chhattisgarh_districts)
                        );
                        binding.district.setAdapter(districtAdapter)
                    }

                    "Goa"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(
                            requireActivity(), android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_goa_districts)
                        );
                        binding.district.setAdapter(districtAdapter)
                    }

                    "Gujarat"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(
                            requireActivity(), android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_gujarat_districts)
                        );
                        binding.district.setAdapter(districtAdapter)
                    }

                    "Haryana"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(
                            requireActivity(), android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_haryana_districts)
                        );
                        binding.district.setAdapter(districtAdapter)
                    }

                    "Himachal Pradesh"-> {
                        val districtAdapter:ArrayAdapter<String> =  ArrayAdapter(
                            requireActivity(), android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_himachal_pradesh_districts));
                        binding.district.setAdapter(districtAdapter)
                    }

                    "Jharkhand"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_jharkhand_districts));
                        binding.district.setAdapter(districtAdapter)}

                    "Karnataka"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_karnataka_districts));
                        binding.district.setAdapter(districtAdapter)}

                    "Kerala"->{
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_kerala_districts));
                        binding.district.setAdapter(districtAdapter)}

                    "Madhya Pradesh"->{
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_madhya_pradesh_districts));
                        binding.district.setAdapter(districtAdapter)}

                    "Maharashtra"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_maharashtra_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Manipur"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_manipur_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Meghalaya"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_meghalaya_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Mizoram"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_mizoram_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Nagaland"->{
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_nagaland_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Odisha"->{
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_odisha_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Punjab"->{
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_punjab_districts));
                        binding.district.setAdapter(districtAdapter);
                    }
                    "Rajasthan"->{
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_rajasthan_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Sikkim" -> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_sikkim_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Tamil Nadu"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_tamil_nadu_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Telangana"->{
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_telangana_districts));
                        binding.district.setAdapter(districtAdapter);
                    }
                    "Tripura"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_tripura_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Uttar Pradesh"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_uttar_pradesh_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Uttarakhand"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_uttarakhand_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "West Bengal"->{
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_west_bengal_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Andaman and Nicobar Islands"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_andaman_nicobar_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Chandigarh"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_chandigarh_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Dadra and Nagar Haveli"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_dadra_nagar_haveli_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Daman and Diu"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_daman_diu_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Delhi"-> {
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_delhi_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Jammu and Kashmir"->{
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_jammu_kashmir_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Lakshadweep"->{
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_lakshadweep_districts));
                        binding.district.setAdapter(districtAdapter);
                    }

                    "Ladakh"->{
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_ladakh_districts));
                        binding.district.setAdapter(districtAdapter);
                    }


                    "Puducherry"->{
                        val districtAdapter:ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,
                            resources.getStringArray(R.array.array_puducherry_districts));
                        binding.district.setAdapter(districtAdapter);
                    }




                    else -> {
                        binding.district.error = "Please Select a Valid State";
                    }
                }
            }

        binding.explore.setOnClickListener{
                pDailog.show();
                var query:Query = firestore.collection("Layouts");
                var query1:Query = firestore.collection("Plots");
                if(binding.state.text.isNotBlank() && binding.district.text.isNotBlank() && binding.taluka.text.isNotBlank()){
                    query = firestore.collection("Layouts")
                        .whereEqualTo("state",binding.state.text.toString())
                        .whereEqualTo("district",binding.district.text.toString())
                        .whereEqualTo("taluka",binding.taluka.text.toString());

                    query1 = firestore.collection("Plots")
                        .whereEqualTo("state",binding.state.text.toString())
                        .whereEqualTo("district",binding.district.text.toString())
                        .whereEqualTo("taluka",binding.taluka.text.toString());
                }
                else if(binding.state.text.isNotBlank()){
                    query = firestore.collection("Layouts")
                        .whereEqualTo("state",binding.state.text.toString());
                    query1 = firestore.collection("Plots")
                        .whereEqualTo("state",binding.state.text.toString())

                }
                else if(binding.state.text.isNotBlank() &&  binding.district.text.isNotBlank()){
                    query = firestore.collection("Layouts")
                        .whereEqualTo("state",binding.state.text.toString())
                        .whereEqualTo("district",binding.district.text.toString());

                    query1 = firestore.collection("Plots")
                        .whereEqualTo("state",binding.state.text.toString())
                        .whereEqualTo("district",binding.district.text.toString());

                }

                query.get().addOnSuccessListener {
                    it.documents.iterator().forEach { documentSnapshot ->
                        val title:String = documentSnapshot.get("title") as String;
                        val seller:String = documentSnapshot.get("sellerName") as String;
                        val totalPlots:String = documentSnapshot.get("totalPlots") as String;
                        val address:String = documentSnapshot.get("address") as String;
                        val longitude:String = documentSnapshot.get("longitude") as String;
                        val latitude:String = documentSnapshot.get("latitude") as String;


                        val card:PropertyCardModel =
                            PropertyCardModel(documentSnapshot.id.toString(), "Null",
                                title,seller,address,"",totalPlots,latitude,longitude);

                        cards.add(card);
                    }

                    adapter.notifyDataSetChanged();


                    pDailog.dismiss();
                }.addOnSuccessListener {
                    query1.get().addOnSuccessListener {
                        it.documents.iterator().forEach { documentSnapshot ->
                            val title:String = documentSnapshot.get("title") as String;
                            val seller:String = documentSnapshot.get("sellerName") as String;
                            val totalPlots:String = documentSnapshot.get("totalPlots") as String;
                            val address:String = documentSnapshot.get("address") as String;
                            val longitude:String = documentSnapshot.get("longitude") as String;
                            val latitude:String = documentSnapshot.get("latitude") as String;


                            val card:PropertyCardModel =
                                PropertyCardModel(documentSnapshot.id.toString(), "Null",
                                    title,seller,address,"",totalPlots,latitude,longitude);

                            cards.add(card);
                        }

                        adapter.notifyDataSetChanged();

                        binding.locationRv.visibility = View.VISIBLE;
                        binding.searchLayout.visibility = View.GONE;
                        binding.explore.visibility = View.GONE;
                        binding.toolbar.visibility = View.VISIBLE;
                    }
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