package com.godspeed.propmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Adapters.DocumentAdapter
import com.godspeed.propmart.Models.DocumentModel
import com.godspeed.propmart.databinding.ActivityPropertyPageBinding
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class PropertyPageActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPropertyPageBinding;
    private lateinit var firebase: FirebaseFirestore;
    private lateinit var documentList: ArrayList<DocumentModel>;
    private lateinit var adapter: DocumentAdapter;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPropertyPageBinding.inflate(layoutInflater);
        setContentView(binding.root)
        documentList = ArrayList<DocumentModel>()
        adapter = DocumentAdapter(this,documentList);

        //Initializing the Activity
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




    }
}