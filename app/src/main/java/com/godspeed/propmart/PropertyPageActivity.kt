package com.godspeed.propmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Adapters.DocumentAdapter
import com.godspeed.propmart.Models.DocumentModel
import com.godspeed.propmart.databinding.ActivityPropertyPageBinding
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.log

class PropertyPageActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPropertyPageBinding;
    private lateinit var firebase: FirebaseFirestore;
    private lateinit var documentList: ArrayList<DocumentModel>;
    private lateinit var adapter: DocumentAdapter;
    private lateinit var plotList: ArrayList<String>;
    private lateinit var idList: ArrayList<String>;
    private lateinit var selectedId:String;
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPropertyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initializing the Activity
        documentList = ArrayList<DocumentModel>()
        adapter = DocumentAdapter(this,documentList);
        plotList = ArrayList<String>();
        idList = ArrayList<String>();
        var map:HashMap<String,String>  = HashMap<String,String>();
        firebase = FirebaseFirestore.getInstance();
        binding.title.text = intent.getStringExtra("title");
        binding.toolbarTitle.text = intent.getStringExtra("title");
        val layoutId:String = intent.getStringExtra("layoutId").toString();

        firebase.collection("Layouts").document(layoutId).get()
            .addOnSuccessListener { snapshot ->
                binding.availablePlots.text = snapshot.get("availablePlots").toString()
                binding.address.text = snapshot.get("address").toString();
                binding.soldPlots.text = snapshot.get("soldPlots").toString();
                binding.sellerName.text = snapshot.get("sellerName").toString();
                binding.totalPlots.text = snapshot.get("totalPlots").toString();
        }

        Log.d("plotList",Arrays.toString(plotList.toArray()));



        //Initializing Dropdown Select
        val plotRef:CollectionReference = firebase.collection("Layouts").document(layoutId).collection("plots");
        plotRef.get().addOnSuccessListener {
            var list:ArrayList<String> = ArrayList<String>()

            it.documents.iterator().forEach { document ->
                Log.e("Inside Loop","");
                Log.e("TITLE",document.getString("title").toString())
                Log.e("ID",document.id)
                list.add(document.getString("title").toString());
                map.put(document.getString("title").toString(),document.id);

            }
            val plotAdapter = ArrayAdapter(this,R.layout.plot_dropdown_item,list);
            binding.plotDropdown.setAdapter(plotAdapter);
            binding.plotDropdown.addTextChangedListener {

                selectedId =  map.get(binding.plotDropdown.text.toString()).toString();
                Log.e("selectedId", selectedId);

            }
            plotAdapter.notifyDataSetChanged();
        }

        //Attaching Document Adapter to RecyclerView
        binding.documentRv.layoutManager = LinearLayoutManager(this
        ,RecyclerView.VERTICAL,false);
        binding.documentRv.adapter = adapter;


        //Getting List of Documents
        val ref:CollectionReference = firebase.collection("Layouts").document(layoutId).collection("documents");
        ref.get().addOnSuccessListener { it ->
            it.documents.iterator().forEach { document->
                var documentModel: DocumentModel = DocumentModel(
                    document.getString("title").toString(),
                    document.getString("downloadUrl").toString())
                if (documentModel != null) {
                    documentList.add(documentModel);
                };
            }
            adapter.notifyDataSetChanged();
        }

        binding.bid.setOnClickListener{
            if(binding.plotDropdown.text.toString() == "Select Plot"){
                binding.plotDropdown.error = "Please Select a Plot";
            }
            else{
                var availability: String
                db.collection("Layouts").document("sample_layout")
                    .collection("plots").document("plot" + binding.plotDropdown.text.toString().substring(5)).get().addOnSuccessListener { snapshot->
                        availability = snapshot["available"].toString()
                        if(availability=="true")
                        {
                            val intent = Intent(this, Plotpage::class.java);
                            Log.d("drop", binding.plotDropdown.text.toString())
                            intent.putExtra("plotId",binding.plotDropdown.text.toString());
                            intent.putExtra("layoutId",layoutId);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(this, "Plot Sold !!", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }

        binding.backButton.setOnClickListener {
            this.finish();
        }

    }
}


