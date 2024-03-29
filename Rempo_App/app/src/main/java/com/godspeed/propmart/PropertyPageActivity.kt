package com.godspeed.propmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout.make
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Adapters.DocumentAdapter
import com.godspeed.propmart.Models.DocumentModel
import com.godspeed.propmart.Models.PropertyCardModel
import com.godspeed.propmart.databinding.ActivityPropertyPageBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
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
    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPropertyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.propertypagrProgress.visibility = VISIBLE
        //Initializing the Activity
        documentList = ArrayList<DocumentModel>()
        adapter = DocumentAdapter(this,documentList);
        plotList = ArrayList<String>();
        idList = ArrayList<String>();
        val map:HashMap<String,String>  = HashMap<String,String>();
        firebase = FirebaseFirestore.getInstance();
        val bundle :Bundle ?=intent.extras
        binding.title.text = bundle?.getString("title");
        binding.toolbarTitle.text = bundle?.getString("title");
        val layoutIds:String = bundle?.getString("layoutId").toString();
        var plotId:String = bundle?.getString("plotId").toString();

        if(layoutIds != "Null")
        {
            firebase.collection("Layouts").document(layoutIds).get()
                .addOnSuccessListener { snapshot ->
                    binding.availablePlots.text = snapshot.get("availablePlots").toString();
                    binding.address.text = snapshot.get("address").toString();
                    binding.soldPlots.text = snapshot.get("soldPlots").toString();
                    binding.sellerName.text = snapshot.get("sellerName").toString();
                    binding.totalPlots.text = snapshot.get("totalPlots").toString();
                    binding.propertypagrProgress.visibility = GONE
                }.addOnFailureListener {
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                }
            binding.bookmarkpropertypage.setOnClickListener {
                db.collection("Layouts").document(layoutIds).get().addOnSuccessListener{
                    val title:String = "Layout At " + it["surveyNo"].toString();
                    val seller:String = it["sellerName"].toString();
                    val plotnumber:String = it["totalPlots"].toString();
                    val address:String = it["District"].toString();
                    val talkua:String = it["Taluka"].toString();
//                val longitude:String = documentSnapshot.get("longitude") as String;
//                val latitude:String = documentSnapshot.get("latitude") as String;
                    val plotImage:String = it["soldPlots"].toString();
                    val card =
                        PropertyCardModel(layoutIds, "Null", title, seller, address,
                            plotImage, plotnumber, "", "",talkua);
                    firestore.collection("Users").document(Firebase.auth.currentUser?.uid.toString())
                        .collection("saved")
                        .document("Layout_$layoutIds").set(card).addOnSuccessListener {
                            binding.bookmarkpropertypage.visibility = View.GONE;
                            binding.bookmarksaved.visibility = View.VISIBLE;
//                       Toast.make(this,"Added to Bookmarks", Toast.LENGTH_SHORT).show();
                        }
                    adapter.notifyDataSetChanged();
                }
            }
            db.collection("Layouts").document(layoutIds).get().addOnSuccessListener {
                if(it["Nakasha"].toString().isNotEmpty())
                {
                    val docname = "Nakasha"
                    val docurl = it["Nakasha"].toString()
                    val docCard = DocumentModel(docname, docurl)
                    documentList.add(docCard)
                }
                if(it["712"].toString().isNotEmpty())
                {
                    val docname = "7/12"
                    val docurl = it["712"].toString()
                    val docCard = DocumentModel(docname, docurl)
                    documentList.add(docCard)
                }
                if(it["NA_Order"].toString().isNotEmpty())
                {
                    val docname = "NA Order"
                    val docurl = it["NA_Order"].toString()
                    val docCard = DocumentModel(docname, docurl)
                    documentList.add(docCard)
                }
                if(it["Other"].toString().isNotEmpty())
                {
                    val docname = "Other"
                    val docurl = it["Other"].toString()
                    val docCard = DocumentModel(docname, docurl)
                    documentList.add(docCard)
                }
                adapter.notifyDataSetChanged()
            }

            binding.bookmarksaved.setOnClickListener {
                firestore.collection("Users").document(Firebase.auth.currentUser?.uid.toString()).collection("saved")
                    .document("Layout_$layoutIds").delete().addOnSuccessListener {
                        binding.bookmarksaved.visibility = View.GONE;
                        binding.bookmarkpropertypage.visibility = View.VISIBLE;
//                    Snackbar.make(binding.root,"Removed from Bookmarks, Visit Homepage to Update",Snackbar.LENGTH_LONG).show();
                    }
            }
        }
        else
        {
            firebase.collection("Plots").document(plotId).get()
                .addOnSuccessListener { snapshot ->
                    binding.plotTotalplot.text = "Plot No: "
                    binding.available.visibility = GONE
                    binding.plotdrp.visibility = GONE
                    binding.soldtt.visibility = GONE
                    binding.availablePlots.visibility = GONE
                    binding.address.text = snapshot.get("Road").toString();
                    binding.soldPlots.visibility = GONE
                    binding.sellerName.text = snapshot.get("Owner Name").toString();
                    binding.totalPlots.text = snapshot.get("Plot No").toString();
                    binding.propertypagrProgress.visibility = GONE
                }.addOnFailureListener {
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                }

            db.collection("Plots").document(plotId).get().addOnSuccessListener {
                if(it["Nakasha"].toString().isNotEmpty())
                {
                    val docname = "Nakasha"
                    val docurl = it["Nakasha"].toString()
                    val docCard = DocumentModel(docname, docurl)
                    documentList.add(docCard)
                }
                if(it["712"].toString().isNotEmpty())
                {
                    val docname = "7/12"
                    val docurl = it["712"].toString()
                    val docCard = DocumentModel(docname, docurl)
                    documentList.add(docCard)
                }
                if(it["NA Order"].toString().isNotEmpty())
                {
                    val docname = "NA Order"
                    val docurl = it["NA Order"].toString()
                    val docCard = DocumentModel(docname, docurl)
                    documentList.add(docCard)
                }
                if(it["Other"].toString().isNotEmpty())
                {
                    val docname = "Other"
                    val docurl = it["Other"].toString()
                    val docCard = DocumentModel(docname, docurl)
                    documentList.add(docCard)
                }
                adapter.notifyDataSetChanged()
            }

            binding.bookmarkpropertypage.setOnClickListener {
                db.collection("Plots").document(plotId).get().addOnSuccessListener{
//                val title:String = documentSnapshot.get("Area") as String;
                    val seller:String = it["Owner Name"] as String;
                    val plotnumber:String = it["Plot No"].toString();
                    val address:String = it["District"] as String;
                    val taluka = it["Taluka"].toString()
//                val longitude:String = documentSnapshot.get("longitude") as String;
//                val latitude:String = documentSnapshot.get("latitude") as String;
                    val plotImage:String = it["Uid"].toString();
                    val card =
                        PropertyCardModel("Null", plotId, "title", seller, address,
                            plotImage, plotnumber, "", "", taluka);
                    firestore.collection("Users").document(Firebase.auth.currentUser?.uid.toString())
                        .collection("saved")
                        .document("Plot_$plotId").set(card).addOnSuccessListener {
                            binding.bookmarkpropertypage.visibility = View.GONE;
                            binding.bookmarksaved.visibility = View.VISIBLE;
//                       Toast.make(this,"Added to Bookmarks", Toast.LENGTH_SHORT).show();
                        }
                    adapter.notifyDataSetChanged();
                }
            }
            binding.bookmarksaved.setOnClickListener {
                firestore.collection("Users").document(Firebase.auth.currentUser?.uid.toString()).collection("saved")
                    .document("Plot_$plotId").delete().addOnSuccessListener {
                        binding.bookmarksaved.visibility = View.GONE;
                        binding.bookmarkpropertypage.visibility = View.VISIBLE;
//                    Snackbar.make(binding.root,"Removed from Bookmarks, Visit Homepage to Update",Snackbar.LENGTH_LONG).show();
                    }
            }
        }

//        Log.d("plotList",Arrays.toString(plotList.toArray()));
        //Initializing Dropdown Select
        val plotRef:CollectionReference = firebase.collection("Layouts").document(layoutIds).collection("Plots");
        val q: Query = plotRef.orderBy("index");
        q.get().addOnSuccessListener {
            val list:ArrayList<String> = ArrayList<String>()

            it.documents.iterator().forEach { document ->
                list.add(document.id);
                map[document.id] = document.id;
            }
            val plotAdapter = ArrayAdapter(this,R.layout.plot_dropdown_item,list);
            binding.plotDropdown.setAdapter(plotAdapter);
            binding.plotDropdown.addTextChangedListener {
                selectedId =  map.get(binding.plotDropdown.text.toString()).toString();
            }
            plotAdapter.notifyDataSetChanged();
        }

        //Attaching Document Adapter to RecyclerView
        binding.documentRv.layoutManager = LinearLayoutManager(this
        ,RecyclerView.VERTICAL,false);
        binding.documentRv.adapter = adapter;



        //Getting List of Documents
//        val ref:CollectionReference = firebase.collection("Plots")
//            .document(plotId).collection("documents");
//        ref.get().addOnSuccessListener { it ->
//            it.documents.iterator().forEach { document->
//                val documentModel: DocumentModel = DocumentModel(
//                    document.getString("title").toString(),
//                    document.getString("downloadUrl").toString())
//                documentList.add(documentModel);
//            }
//            adapter.notifyDataSetChanged();
//        }

        val docRef = firestore
            .collection("Users/" + Firebase.auth.currentUser?.uid.toString() + "/saved")
            .get().addOnSuccessListener { snapshot->
                if(snapshot.isEmpty)
                {
                    binding.bookmarkpropertypage.visibility = VISIBLE
                    binding.bookmarksaved.visibility = GONE
                }
                else {
                    binding.bookmarkpropertypage.visibility = GONE
                    binding.bookmarksaved.visibility = VISIBLE
                }
            }


        binding.bid.setOnClickListener{
            if(layoutIds!="Null")
            {
                if(binding.plotDropdown.text.toString() == "Select Plot"){
                    binding.plotDropdown.error = "Please Select a Plot";
                }
                else {
                    var availability: String
                    db.collection("Layouts").document(layoutIds)
                        .collection("Plots")
                        .document("Plot" + binding.plotDropdown.text.toString().substring(4))
                        .get().addOnSuccessListener { snapshot ->
                            availability = snapshot["available"].toString()
                            if (availability == "Available") {
                                val intent = Intent(this, Plotpage::class.java);
                                intent.putExtra("plotId", binding.plotDropdown.text.toString());
                                intent.putExtra("layoutId", layoutIds);
                                startActivity(intent);
                            } else {
                                Toast.makeText(this, "Plot Sold !!", Toast.LENGTH_SHORT).show()
                            }
                        }.addOnFailureListener {
                            Toast.makeText(this, "Some Internal Error Occurred !!", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            else
            {
                db.collection("Plots").document(plotId)
                    .get().addOnSuccessListener {
                        val intent = Intent(this, Plotpage::class.java);
                        intent.putExtra("plotId", plotId);
                        intent.putExtra("layoutId", "Null");
                        startActivity(intent);
                    }.addOnFailureListener {
                        Toast.makeText(this, "Some Internal Error Occurred !!", Toast.LENGTH_SHORT).show()
                    }
            }
        }
        binding.backButton.setOnClickListener {
            this.finish();
        }

    }
}


