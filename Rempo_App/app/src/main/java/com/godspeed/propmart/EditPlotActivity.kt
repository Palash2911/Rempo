package com.godspeed.propmart

import android.R
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Adapters.DocumentEditAdapter
import com.godspeed.propmart.Models.DocumentEditModel
import com.godspeed.propmart.databinding.ActivityEditPlotBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class EditPlotActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var binding: ActivityEditPlotBinding
    lateinit var selectedPdf:Uri
    lateinit var layoutId:String
    private lateinit var documentList: ArrayList<DocumentEditModel>;
    private lateinit var adapter: DocumentEditAdapter;
    lateinit var plotId:String
    lateinit var firestore: FirebaseFirestore;
    val map:HashMap<String,String> = HashMap();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPlotBinding.inflate(layoutInflater);
        setContentView(binding.root)
        val bundle :Bundle ?=intent.extras
        layoutId = bundle?.getString("layoutId").toString();
        plotId = bundle?.getString("plotId").toString();
        firestore = FirebaseFirestore.getInstance();
        documentList = ArrayList<DocumentEditModel>()
        adapter = DocumentEditAdapter(this,documentList);
//        val title = intent.getStringExtra("title");
        binding.documentRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        binding.documentRv.adapter = adapter;
        binding.fullprogress.visibility = VISIBLE
        binding.toolbarTitle.text = title;

        binding.upload.setOnClickListener {
            val pdfIntent:Intent = Intent();
            pdfIntent.setAction(Intent.ACTION_GET_CONTENT);
            pdfIntent.setType("application/pdf");
            startActivityForResult(pdfIntent,25);
        }

        if(layoutId == "Null")
        {
            firestore.collection("Plots").document(plotId).get().addOnSuccessListener {
                binding.title.text = it["District"].toString()
                binding.sellerName.text = "By - " + it["Owner Name"].toString()
                binding.Plotstv.text = "Plot No: "
                binding.totalPlots.text = it["Plot No"].toString()
                binding.availableTv.visibility = GONE
                binding.availablePlots.visibility = GONE
                binding.SoldTv.visibility = GONE
                binding.soldPlots.visibility = GONE
                binding.address.text = it["Road"].toString()
                binding.area.text = it["Area"].toString() + "sq.m"
                binding.dimensions.text = it["Front"].toString() + "sq.m x " +it["Depth"].toString() + "sq.m"
                binding.rate.text = it["Bid Price"].toString()
                binding.description.visibility = GONE
                binding.fullprogress.visibility = GONE
            }.addOnFailureListener {
                Toast.makeText(this, "Some Internal Error Occurred !", Toast.LENGTH_SHORT).show()
            }
        }

        firestore.collection("Plots").document("csskjIpbigJ9YW661RKQ").get().addOnSuccessListener {
            if(it["Nakasha"].toString().isNotEmpty())
            {
                val docname = "Nakasha"
                val docurl = it["Nakasha"].toString()
                val docCard = DocumentEditModel(docname, docurl)
                documentList.add(docCard)
            }
            if(it["712"].toString().isNotEmpty())
            {
                val docname = "7/12"
                val docurl = it["712"].toString()
                val docCard = DocumentEditModel(docname, docurl)
                documentList.add(docCard)
            }
            if(it["NA Order"].toString().isNotEmpty())
            {
                val docname = "NA Order"
                val docurl = it["NA Order"].toString()
                val docCard = DocumentEditModel(docname, docurl)
                documentList.add(docCard)
            }
            if(it["Other"].toString().isNotEmpty())
            {
                val docname = "Other"
                val docurl = it["Other"].toString()
                val docCard = DocumentEditModel(docname, docurl)
                documentList.add(docCard)
            }
            adapter.notifyDataSetChanged()
        }

        var selectplotCat: ArrayList<String> = ArrayList()
//        selectplotCat.add("Select Property Category")
        selectplotCat.add("NA Plot")
        selectplotCat.add("Agricultural Land")
        selectplotCat.add("Guntha Plot")

        var selectsubplotCat: ArrayList<String> = ArrayList()
//        selectsubplotCat.add("Select Sub-Property Category")
        selectsubplotCat.add("Residential Plot")
        selectsubplotCat.add("Commercial Plot")
        selectsubplotCat.add("Residential cum Commercial Plot")

        binding.plotSpinner.onItemSelectedListener = this
        var adap2 = ArrayAdapter<String>(this, R.layout.simple_spinner_item, selectplotCat)
        adap2.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
        binding.plotSpinner.adapter = adap2

        binding.subPropSpinner.onItemSelectedListener = this
        var adap3 = ArrayAdapter<String>(this, R.layout.simple_spinner_item, selectsubplotCat)
        adap3.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
        binding.subPropSpinner.adapter = adap3

        binding.editScheme.setOnClickListener{
            binding.editSchemePopup.visibility = VISIBLE;
            binding.editSchemell.visibility = GONE
            binding.editSchemeProgress.visibility = VISIBLE
            firestore.collection("Plots").document(plotId).get().addOnSuccessListener {
                binding.ownerName.setText(it["Owner Name"].toString())
                binding.district.setText(it["District"].toString())
                binding.editTextTaluka.setText(it["Taluka"].toString())
                binding.editTextVillage.setText(it["Village"].toString())
                binding.editTextAddress.setText(it["Road"].toString())
                if(it["Property Category"].toString() == "Agricultural Land")
                {
                    binding.plotSpinner.setSelection(1)
                }
                else if(it["Property Category"].toString() == "NA Plot")
                {
                    binding.plotSpinner.setSelection(0)
                    binding.subPropSpinner.visibility = VISIBLE
                    binding.subProptv.visibility = VISIBLE
                    if(it["Sub-Property Category"].toString() == "Residential Plot")
                    {
                        binding.subPropSpinner.setSelection(0)
                    }
                    else if(it["Sub-Property Category"].toString() == "Commercial Plot")
                    {
                        binding.subPropSpinner.setSelection(1)
                    }
                    else
                    {
                        binding.subPropSpinner.setSelection(2)
                    }
                }
                else{
                    binding.plotSpinner.setSelection(2)
                }
                binding.editSchemeProgress.visibility = GONE
                binding.editSchemell.visibility = VISIBLE
            }.addOnFailureListener {
                Toast.makeText(this, "Some Internal Error Occurred !", Toast.LENGTH_SHORT).show()
            }
        }

        binding.cancel.setOnClickListener{
            binding.editSchemePopup.visibility = View.GONE
        }

        binding.save.setOnClickListener {

         val dialog:ProgressDialog = ProgressDialog(this);
         dialog.setCancelable(false);
            dialog.setMessage("Please Wait");
            dialog.show();

         val owner:String = binding.ownerName.text.toString();
         val address:String = binding.editTextAddress.text.toString();
         val district:String = binding.district.text.toString();
         val village:String = binding.editTextVillage.text.toString();
         val taluka:String = binding.editTextTaluka.text.toString();

            map["Owner Name"] = owner;
            map["District"] = district;
            map["Road"] = address;
            map["Village"] = village;
            map["Taluka"] = taluka;

            firestore.collection("Plots").document(plotId)
                .update(map as Map<String, Any>).addOnSuccessListener {
                    Toast.makeText(this,"Details Updated Successfully",Toast.LENGTH_SHORT);
                    binding.editSchemePopup.visibility = View.GONE;
                    dialog.dismiss();
                    finish();
                    startActivity(intent);
                }.addOnFailureListener {
                    Toast.makeText(this, "Some Internal Error Occurred !", Toast.LENGTH_SHORT).show()
                }
        }

        binding.editPlotDetails.setOnClickListener {
            binding.editDetailsPopup.visibility = VISIBLE
            binding.editdetailsll.visibility = GONE
            binding.editdepro.visibility = VISIBLE

            firestore.collection("Plots").document(plotId).get().addOnSuccessListener{
                binding.surveyNo.setText(it["Survey No"].toString())
                binding.plotNo.setText(it["Plot No"].toString())
                binding.aSize.setText(it["Area"].toString())
                binding.fron.setText(it["Front"].toString())
                binding.dept.setText(it["Depth"].toString())
                binding.road.setText(it["Road"].toString())
                binding.editdetailsll.visibility = VISIBLE
                binding.editdepro.visibility = GONE
            }.addOnFailureListener {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        binding.savePlot.setOnClickListener {
            val dialog:ProgressDialog = ProgressDialog(this);
            dialog.setCancelable(false);
            dialog.setMessage("Please Wait");
            dialog.show();

            val sno:String = binding.surveyNo.text.toString();
            val pno:String = binding.plotNo.text.toString();
            val asi:String = binding.aSize.text.toString();
            val fro:String = binding.fron.text.toString();
            val dept:String = binding.dept.text.toString();
            val road:String = binding.road.text.toString()

            map["Survey No"] = sno;
            map["Plot No"] = pno;
            map["Front"] = fro;
            map["Area"] = asi;
            map["Depth"] = dept;
            map["Road"] = road

            firestore.collection("Plots").document(plotId)
                .update(map as Map<String, Any>).addOnSuccessListener {
                    Toast.makeText(this,"Details Updated Successfully",Toast.LENGTH_SHORT);
                    binding.editDetailsPopup.visibility = View.GONE;
                    dialog.dismiss();
                    finish();
                    startActivity(intent);
                }.addOnFailureListener {
                    Toast.makeText(this, "Some Internal Error Occurred !", Toast.LENGTH_SHORT).show()
                }
        }
        binding.cancelPlot.setOnClickListener {
            binding.editDetailsPopup.visibility = GONE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val dialog:ProgressDialog = ProgressDialog(this);
        val date: Date = Date();
        selectedPdf = data?.data!!

        if(selectedPdf!=null){
            dialog.show();
            val ref:StorageReference = FirebaseStorage.getInstance().reference
                .child("Documents").child(layoutId+"").child(date.time.toString());
            ref.putFile(selectedPdf).addOnCompleteListener(OnCompleteListener { task ->
                if(task.isSuccessful){
                    ref.downloadUrl.addOnSuccessListener(OnSuccessListener { uri ->
                         val downloadUrl:String = uri.toString();
                    })
                }
            })
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val item = p0!!.selectedItem.toString()
        if(item == "NA Plot" || (item=="Residential Plot" || item=="Commercial Plot" || item=="Residential cum Commercial Plot"))
        {
            val plot = "NA Plot"
            var subPlot = ""
            map["Property Category"] = plot
            when (item) {
                "Residential Plot" -> {
                    subPlot = "Residential Plot"
                }
                "Commercial Plot" -> {
                    subPlot = "Commercial Plot"
                }
                "Residential cum Commercial Plot" -> {
                    subPlot = "Residential cum Commercial Plot"
                }
            }
            map["Sub-Property Category"] = subPlot
            binding.subPropSpinner.visibility = VISIBLE
            binding.subProptv.visibility = VISIBLE
        }
        else
        {
            if(item=="Agricultural Land")
            {
                map["Property Category"] = "Agricultural Land"
                map["Sub-Property Category"] = ""
            }
            else if(item=="Guntha Plot")
            {
                map["Property Category"] = "Guntha Plot"
                map["Sub-Property Category"] = ""
            }
            binding.subPropSpinner.visibility = GONE
            binding.subProptv.visibility = GONE
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}