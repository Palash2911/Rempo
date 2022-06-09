package com.godspeed.propmart

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.godspeed.propmart.databinding.ActivityBiddingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.HashMap

class BiddingActivity : AppCompatActivity() {

    private lateinit var binding:ActivityBiddingBinding;
    private lateinit var firestore: FirebaseFirestore;
    private lateinit var auth: FirebaseAuth;



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBiddingBinding.inflate(layoutInflater);

        super.onCreate(savedInstanceState)
        setContentView(binding.root);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        val pDailog = ProgressDialog(this);
        pDailog.setMessage("Please Wait");
        pDailog.setCancelable(false);

        val plotId = intent.getStringExtra("plotId").toString();
        val layoutId = intent.getStringExtra("layoutId").toString();
        val plotRef = firestore.collection("Layouts")
            .document(layoutId).collection("plots").document(plotId);

        plotRef.get().addOnSuccessListener { plot ->
            binding.plotNo.text = "Bid on "+plot.get("title");
            binding.area.text = plot.get("totalArea").toString();
            binding.dimensions.text = plot.get("dimension").toString();
            binding.description.text = plot.get("description").toString();
            binding.rate.text = plot.get("rate").toString();


            if(!plot.getBoolean("available")!!){
                binding.placebidbtn.isEnabled = false;
                binding.placebidbtn.text = "SOLD OUT"
                binding.bidAmount.isEnabled = false;
            }
        }

//        binding.placebidbtn.setOnClickListener{
//            if(binding.bidAmount.text.isNullOrEmpty()){
//                binding.bidAmount.setError("Please Enter Bid Amount");
//            }
//            else{
//
//                pDailog.show();
//                val map:HashMap<String,Object> = HashMap();
//                map.put("")
//                plotRef.collection("bids").document(auth.uid.toString()).set(map)
//                    .addOnSuccessListener {
//
//                    }
//            }
//        }


    }
}