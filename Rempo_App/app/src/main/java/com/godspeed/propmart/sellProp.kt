package com.godspeed.propmart

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.get
import com.godspeed.propmart.databinding.ActivitySellPropBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


class sellProp : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var binding: ActivitySellPropBinding
    var naPlot = false
    private val db = Firebase.firestore
    private lateinit var storage: FirebaseStorage
    private val auth = FirebaseAuth.getInstance()
    val newPlot:HashMap<String, Any> = HashMap()
    var i=0
    var doc1 = 0
    var doc2 = 0
    var doc3 = 0
    var doc4 = 0
    var pr=0
    var spr=0
    private lateinit var statesel: AutoCompleteTextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellPropBinding.inflate(layoutInflater)
        setContentView(binding.root)

        statesel = binding.statetv
        val stateArray = resources.getStringArray(R.array.array_indian_states)

        val stateAdapter: ArrayAdapter<String> = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,stateArray);
        statesel.setAdapter(stateAdapter);
        statesel.threshold = 1;

        val selectplotCat: ArrayList<String> = ArrayList()
        selectplotCat.add("Select Property Category")
        selectplotCat.add("Agricultural Land")
        selectplotCat.add("NA Plot")
        selectplotCat.add("Guntha Plot")

        val selectsubplotCat: ArrayList<String> = ArrayList()
        selectsubplotCat.add("Select Sub-Property Category")
        selectsubplotCat.add("Residential Plot")
        selectsubplotCat.add("Commercial Plot")
        selectsubplotCat.add("Residential cum Commercial Plot")

        val area: ArrayList<String> = ArrayList()
        area.add("Sq Ft")
        area.add("H.R ")
        area.add("Sq Mt")
        area.add("Acres")

        val bid: ArrayList<String> = ArrayList()
        bid.add("Sq Ft")
        bid.add("Sq Mt")
        bid.add("Acres")

        val fr: ArrayList<String> = ArrayList()
        fr.add("Ft")
        fr.add("Mt")

        binding.frSpinner.onItemSelectedListener = this
        val adap0 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fr)
        adap0.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
        binding.frSpinner.adapter = adap0

        binding.areaSpinner.onItemSelectedListener = this
        val adap1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, area)
        adap1.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
        binding.areaSpinner.adapter = adap1

        binding.plotSpinner.onItemSelectedListener = this
        val adap2 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, selectplotCat)
        adap2.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
        binding.plotSpinner.adapter = adap2

        binding.subProprSpinner.onItemSelectedListener = this
        val adap3 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, selectsubplotCat)
        adap3.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
        binding.subProprSpinner.adapter = adap3

        binding.bidSpinner.onItemSelectedListener = this
        val adap4 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bid)
        adap4.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
        binding.bidSpinner.adapter = adap4

        binding.back1.setOnClickListener {

            if(newPlot["Property Category"] == "NA Plot")
            {
                binding.subProprSpinner.visibility = VISIBLE
                binding.textView13.visibility = VISIBLE
            }
            binding.CardView2.visibility = GONE
            binding.CardView1.visibility = VISIBLE
        }

        binding.Next1.setOnClickListener {
            var fl1=0
            var fl2=0
            var fl3=0
            var fl4=0
            var fl5=0
            if(binding.ownerName.text.toString().isEmpty())
            {
                binding.ownerName.error = "Required"
            }
            else
            {
                fl1=1
                newPlot["Owner Name"] = binding.ownerName.text.toString()
            }
            if(binding.districttv.text.toString().isEmpty())
            {
                binding.districttv.error = "Required"
            }
            else
            {
                fl2=1
                newPlot["District"] = binding.districttv.text.toString()
            }
            if(binding.taluka1.text.toString().isEmpty())
            {
                binding.taluka1.error = "Required"
            }
            else
            {
                fl3=1
                newPlot["Taluka"] = binding.taluka1.text.toString()
            }
            if(binding.village.text.toString().isEmpty())
            {
                binding.village.error = "Required"
            }
            else
            {
                fl4=1
                newPlot["Village"] = binding.village.text.toString()
            }
            if(binding.statetv.text.toString().isEmpty())
            {
                binding.statetv.error = "Required"
            }
            else
            {
                fl5=1
                statesel.onItemClickListener = AdapterView
                    .OnItemClickListener{
                            parent,view,position,id->
                        val selectedItem = parent.getItemAtPosition(position)
                            .toString()
                        when(selectedItem){

                            "Andhra Pradesh" ->{

                                val array = resources.getStringArray(R.array.array_andhra_pradesh_districts);
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(
                                    this, android.R.layout.simple_list_item_1,
                                    array
                                );
                                binding.districttv.setAdapter(districtAdapter)}

                            "Arunachal Pradesh"-> {

                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(
                                    this, android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_arunachal_pradesh_districts)
                                );
                                binding.districttv.setAdapter(districtAdapter)
                            }

                            "Assam"->{
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_assam_districts));
                                binding.districttv.setAdapter(districtAdapter)}

                            "Bihar"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_bihar_districts));
                                binding.districttv.setAdapter(districtAdapter)}

                            "Chhattisgarh"-> {
                                val array = resources.getStringArray(R.array.array_chhattisgarh_districts);
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(
                                    this, android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_chhattisgarh_districts)
                                );
                                binding.districttv.setAdapter(districtAdapter)
                            }

                            "Goa"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(
                                    this, android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_goa_districts)
                                );
                                binding.districttv.setAdapter(districtAdapter)
                            }

                            "Gujarat"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(
                                    this, android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_gujarat_districts)
                                );
                                binding.districttv.setAdapter(districtAdapter)
                            }

                            "Haryana"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(
                                    this, android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_haryana_districts)
                                );
                                binding.districttv.setAdapter(districtAdapter)
                            }

                            "Himachal Pradesh"-> {
                                val districtAdapter:ArrayAdapter<String> =  ArrayAdapter(
                                    this, android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_himachal_pradesh_districts));
                                binding.districttv.setAdapter(districtAdapter)
                            }

                            "Jharkhand"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_jharkhand_districts));
                                binding.districttv.setAdapter(districtAdapter)}

                            "Karnataka"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_karnataka_districts));
                                binding.districttv.setAdapter(districtAdapter)}

                            "Kerala"->{
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_kerala_districts));
                                binding.districttv.setAdapter(districtAdapter)}

                            "Madhya Pradesh"->{
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_madhya_pradesh_districts));
                                binding.districttv.setAdapter(districtAdapter)}

                            "Maharashtra"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_maharashtra_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Manipur"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_manipur_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Meghalaya"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_meghalaya_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Mizoram"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_mizoram_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Nagaland"->{
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_nagaland_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Odisha"->{
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_odisha_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Punjab"->{
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_punjab_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }
                            "Rajasthan"->{
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_rajasthan_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Sikkim" -> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_sikkim_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Tamil Nadu"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_tamil_nadu_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Telangana"->{
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_telangana_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }
                            "Tripura"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_tripura_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Uttar Pradesh"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_uttar_pradesh_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Uttarakhand"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_uttarakhand_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "West Bengal"->{
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_west_bengal_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Andaman and Nicobar Islands"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_andaman_nicobar_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Chandigarh"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_chandigarh_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Dadra and Nagar Haveli"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_dadra_nagar_haveli_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Daman and Diu"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_daman_diu_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Delhi"-> {
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_delhi_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Jammu and Kashmir"->{
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_jammu_kashmir_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Lakshadweep"->{
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_lakshadweep_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            "Ladakh"->{
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_ladakh_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }


                            "Puducherry"->{
                                val districtAdapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                    resources.getStringArray(R.array.array_puducherry_districts));
                                binding.districttv.setAdapter(districtAdapter);
                            }

                            else -> {
                                binding.districttv.error = "Please Select a Valid State";
                            }
                        }
                    }
                newPlot["State"] = binding.statetv.text.toString()
            }
            if(fl1==1 && fl2==1 && fl3==1 && fl4==1 && fl5==1)
            {
                if(pr==0){
                    Toast.makeText(this, "Please Select Property Category! ", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    if(pr==2 && spr==0)
                    {
                        Toast.makeText(this, "Please Select Sub-Property Category! ", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        binding.CardView2.visibility = VISIBLE
                        binding.CardView1.visibility = GONE
                    }
                }
            }
        }

        binding.Next2.setOnClickListener {
            var fl1=0
            var fl2=0
            var fl3=0
            var fl4=0
            var fl5=0
            var fl6=0
            if(binding.surveyNo.text.toString().isEmpty())
            {
                binding.surveyNo.error = "Required"
            }
            else
            {
                fl1=1
            }
            if(binding.location.text.toString().isEmpty())
            {
                binding.location.error = "Required"
            }
            else
            {
                fl2=1
            }
            if(binding.aSize.text.toString().isEmpty())
            {
                binding.aSize.error = "Required"
            }
            else
            {
                fl3=1
            }
            if(binding.fron.text.toString().isEmpty())
            {
                binding.fron.error = "Required"
            }
            else
            {
                fl4=1
            }
            if(binding.dept.text.toString().isEmpty())
            {
                binding.dept.error = "Required"
            }
            else
            {
                fl5=1
            }
            if(binding.road.text.toString().isEmpty())
            {
                binding.road.error = "Required"
            }
            else
            {
                fl6=1
            }
            if(fl1==1 && fl2==1 && fl3==1 && fl4==1 && fl5==1 && fl6==1)
            {
                binding.CardView3.visibility = VISIBLE
                binding.CardView2.visibility = GONE
                newPlot["Survey No"] = binding.surveyNo.text.toString()
                newPlot["Location"] = binding.location.text.toString()
                newPlot["Plot No"] = binding.plotNo.text.toString()
                newPlot["Area"] = binding.aSize.text.toString()
                newPlot["Front"] = binding.fron.text.toString()
                newPlot["Depth"] = binding.dept.text.toString()
                newPlot["Road"] = binding.road.text.toString()
            }
        }

        binding.back2.setOnClickListener {
            binding.CardView3.visibility = GONE
            binding.CardView2.visibility = VISIBLE
        }

        binding.Next3.setOnClickListener {

            newPlot["Bid Price"] = binding.bidamt.text.toString()
            var fl1=0
            if(binding.bidamt.text.toString().isEmpty())
            {
                binding.bidamt.error = "Required"
            }
            else
            {
                fl1=1
            }
            if(fl1==1)
            {
                newPlot["Bid Price"] = binding.bidamt.text.toString()
                newPlot["Uid"] = auth.currentUser?.uid.toString()
                if(doc1==1 || doc2==1 || doc3==1 || doc4==1)
                {
                    val ref: DocumentReference = db.collection("Plots").document()
                    val myId = ref.id
                    if(doc1==0)
                    {
                        newPlot["712"]=""
                    }
                    if(doc2==0)
                    {
                        newPlot["Nakasha"]=""
                    }
                    if(doc3==0)
                    {
                        newPlot["NA Order"]=""
                    }
                    if(doc4==0)
                    {
                        newPlot["Other"]=""
                    }
                    db.collection("Plots").document(myId)
                        .set(newPlot).addOnSuccessListener {
                            binding.llSell.visibility = GONE
                            binding.completeSell.visibility = VISIBLE
                            Toast.makeText(this, "Plot Successfully Added !! ", Toast.LENGTH_SHORT).show()
                        }
                            .addOnFailureListener{
                                    Toast.makeText(this, "Something Went Wrong !! ", Toast.LENGTH_SHORT).show()
                                }
                }
            }
        }

        // UPLOADING DOCUMENTS
        binding.uploadbtn1.setOnClickListener {
            doc1=1
            uploadFile("doc1")
        }
        binding.deletebtn1.setOnClickListener{
            binding.deletebtn1.visibility = GONE
            binding.progressBar4.visibility = VISIBLE
            val storageref = FirebaseStorage.getInstance().getReference("Documents/" + auth.uid.toString() + "_doc1_" + newPlot["Plot No"])
            storageref.delete().addOnSuccessListener {
                newPlot["712"]=""
                Toast.makeText(this,"Deleted", Toast.LENGTH_SHORT).show()
                binding.progressBar4.visibility = GONE
                binding.uploadbtn1.visibility = VISIBLE
                doc1=0
            }.addOnFailureListener {
                Toast.makeText(this,"Something Went Wrong", Toast.LENGTH_SHORT).show()
                binding.deletebtn1.visibility = VISIBLE
                binding.progressBar4.visibility = GONE
            }
        }
        binding.uploadbtn2.setOnClickListener {
            doc2=1
            uploadFile("doc2")
        }
        binding.deletebtn2.setOnClickListener{
            binding.deletebtn2.visibility = GONE
            binding.progressBar5.visibility = VISIBLE
            val storageref = FirebaseStorage.getInstance().getReference("Documents/" + auth.uid.toString() + "_doc2_" + newPlot["Plot No"])
            storageref.delete().addOnSuccessListener {
                newPlot["Nakasha"]=""
                Toast.makeText(this,"Deleted", Toast.LENGTH_SHORT).show()
                binding.progressBar5.visibility = GONE
                binding.uploadbtn2.visibility = VISIBLE
                doc2=0
            }.addOnFailureListener {
                Toast.makeText(this,"Something Went Wrong", Toast.LENGTH_SHORT).show()
                binding.deletebtn2.visibility = VISIBLE
                binding.progressBar5.visibility = GONE
            }
        }
        binding.uploadbtn3.setOnClickListener {
            doc3=1
            uploadFile("doc3")
        }
        binding.deletebtn3.setOnClickListener{
            binding.deletebtn3.visibility = GONE
            binding.progressBar6.visibility = VISIBLE
            val storageref = FirebaseStorage.getInstance().getReference("Documents/" + auth.uid.toString() + "_doc3_" + newPlot["Plot No"])
            storageref.delete().addOnSuccessListener {
                newPlot["NA Order"]=""
                Toast.makeText(this,"Deleted", Toast.LENGTH_SHORT).show()
                binding.progressBar6.visibility = GONE
                binding.uploadbtn3.visibility = VISIBLE
                doc3=0
            }.addOnFailureListener {
                Toast.makeText(this,"Something Went Wrong", Toast.LENGTH_SHORT).show()
                binding.deletebtn3.visibility = VISIBLE
                binding.progressBar6.visibility = GONE
            }
        }
        binding.uploadbtn4.setOnClickListener {
            doc4=1
            uploadFile("doc4")
        }
        binding.deletebtn4.setOnClickListener{
            binding.deletebtn4.visibility = GONE
            binding.progressBar7.visibility = VISIBLE
            val storageref = FirebaseStorage.getInstance().getReference("Documents/" + auth.uid.toString() + "_doc4_" + newPlot["Plot No"])
            storageref.delete().addOnSuccessListener {
                newPlot["Other"]=""
                Toast.makeText(this,"Deleted", Toast.LENGTH_SHORT).show()
                binding.progressBar7.visibility = GONE
                binding.uploadbtn4.visibility = VISIBLE
                doc4=0
            }.addOnFailureListener {
                Toast.makeText(this,"Something Went Wrong", Toast.LENGTH_SHORT).show()
                binding.deletebtn4.visibility = VISIBLE
                binding.progressBar7.visibility = GONE
            }

        }

        binding.visithomeSeller.setOnClickListener {
            val intent = Intent(this, BottomnavSeller::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun uploadFile(docu: String)
    {
        var intent = Intent()
        intent.type = "application/pdf"
        intent.action = Intent.ACTION_GET_CONTENT
        when (docu) {
            "doc1" -> {
                startActivityForResult(intent, 1);
            }
            "doc2" -> {
                startActivityForResult(intent, 2);
            }
            "doc3" -> {
                startActivityForResult(intent, 3);
            }
            "doc4" -> {
                startActivityForResult(intent, 4);
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1 && resultCode == RESULT_OK)
        {
            binding.uploadbtn1.visibility = GONE
            binding.progressBar4.visibility = VISIBLE
            val storageref = FirebaseStorage.getInstance().getReference("Documents/" + auth.uid.toString() + "_doc1_" + newPlot["Plot No"])
            storageref.putFile(data?.data!!).addOnSuccessListener {
                storageref.downloadUrl.addOnSuccessListener{ uri ->
                    val downloadUrl = uri.toString();
                    val docs:HashMap<String, Any> = HashMap()
                    newPlot["712"] = downloadUrl
                    Toast.makeText(this , "File Uploaded Successfully", Toast.LENGTH_SHORT).show()
                    binding.deletebtn1.visibility = VISIBLE
                    binding.progressBar4.visibility = GONE
                }.addOnFailureListener {
                    Log.d("Inner Error", it.toString())
                }
            }.addOnFailureListener{
                Log.d("Outer Error", it.toString())
            }
        }
        else if(requestCode==2 && resultCode == RESULT_OK)
        {
            binding.uploadbtn2.visibility = GONE
            binding.progressBar5.visibility = VISIBLE
            val storageref = FirebaseStorage.getInstance().getReference("Documents/" + auth.uid.toString() + "_doc2_" + newPlot["Plot No"])
            storageref.putFile(data?.data!!).addOnSuccessListener {
                storageref.downloadUrl.addOnSuccessListener{ uri ->
                    val downloadUrl = uri.toString();
                    val docs:HashMap<String, Any> = HashMap()
                    newPlot["Nakasha"] = downloadUrl
                    Toast.makeText(this , "File Uploaded Successfully", Toast.LENGTH_SHORT).show()
                    binding.deletebtn2.visibility = VISIBLE
                    binding.progressBar5.visibility = GONE
                }.addOnFailureListener {
                    Log.d("Inner Error", it.toString())
                }
            }.addOnFailureListener{
                Log.d("Outer Error", it.toString())
            }
        }
        else if(requestCode==3 && resultCode == RESULT_OK)
        {
            binding.uploadbtn3.visibility = GONE
            binding.progressBar6.visibility = VISIBLE
            val storageref = FirebaseStorage.getInstance().getReference("Documents/" + auth.uid.toString() + "_doc3_" + newPlot["Plot No"])
            storageref.putFile(data?.data!!).addOnSuccessListener {
                storageref.downloadUrl.addOnSuccessListener{ uri ->
                    val downloadUrl = uri.toString();
                    val docs:HashMap<String, Any> = HashMap()
                    newPlot["NA Order"] = downloadUrl
                    Toast.makeText(this , "File Uploaded Successfully", Toast.LENGTH_SHORT).show()
                    binding.deletebtn3.visibility = VISIBLE
                    binding.progressBar6.visibility = GONE
                }.addOnFailureListener {
                    Toast.makeText(this, "Some Internal Error Occurred !", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Some Internal Error Occurred !", Toast.LENGTH_SHORT).show()
            }
        }
        else if(requestCode==4 && resultCode == RESULT_OK)
        {
            binding.uploadbtn4.visibility = GONE
            binding.progressBar7.visibility = VISIBLE
            val storageref = FirebaseStorage.getInstance().getReference("Documents/" + auth.uid.toString() + "_doc4_" + newPlot["Plot No"])
            storageref.putFile(data?.data!!).addOnSuccessListener {
                storageref.downloadUrl.addOnSuccessListener{ uri ->
                    val downloadUrl = uri.toString();
                    val docs:HashMap<String, Any> = HashMap()
                    newPlot["Other"] = downloadUrl
                    Toast.makeText(this , "File Uploaded Successfully", Toast.LENGTH_SHORT).show()
                    binding.deletebtn4.visibility = VISIBLE
                    binding.progressBar7.visibility = GONE
                }.addOnFailureListener {
                        Toast.makeText(this, "Some Internal Error Occurred !", Toast.LENGTH_SHORT).show()
                    }
            }.addOnFailureListener {
                    Toast.makeText(this, "Some Internal Error Occurred !", Toast.LENGTH_SHORT).show()
                }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val item = p0!!.selectedItem.toString()
        if(binding.CardView2.visibility== VISIBLE)
        {
            if(item=="Sq Ft" || item=="H.R " || item=="Sq Mt" || item=="Acres")
            {
                newPlot["Area_Un"] = item
            }
            if(item=="Ft" || item=="Mt")
            {
                newPlot["Front_Un"] = item
            }
        }
        if(binding.CardView3.visibility == VISIBLE)
        {
            if(item=="Sq Ft" || item=="Sq Mt" || item=="Acres")
            {
                newPlot["Bids_Un"] = item
            }
        }
//        Toast.makeText(this, "Item Selected $item", Toast.LENGTH_SHORT).show()
        if(item == "NA Plot" || (item=="Residential Plot" || item=="Commercial Plot" || item=="Residential cum Commercial Plot" || item== "Select Sub-Property Category"))
        {

            binding.subProprSpinner.visibility = VISIBLE
            binding.textView13.visibility = VISIBLE
            pr=2
            val plot = "NA Plot"
            var subPlot = ""
            newPlot["Property Category"] = plot
            when (item) {
                "Residential Plot" -> {
                    subPlot = "Residential Plot"
                    spr=1
                }
                "Commercial Plot" -> {
                    subPlot = "Commercial Plot"
                    spr=1
                }
                "Residential cum Commercial Plot" -> {
                    subPlot = "Residential cum Commercial Plot"
                    spr=1
                }
                "Select Sub-Property Category" ->{
                    spr=0
                }
            }
            newPlot["Sub-Property Category"] = subPlot
        }
        else
        {
            if(item=="Agricultural Land")
            {
                newPlot["Property Category"] = "Agricultural Land"
                newPlot["Sub-Property Category"] = ""
                pr=1
            }
            else if(item=="Guntha Plot")
            {
                newPlot["Property Category"] = "Guntha Plot"
                newPlot["Sub-Property Category"] = ""
                pr=3
            }
            else
            {
                pr=0
            }
            binding.subProprSpinner.visibility = GONE
            binding.textView13.visibility = GONE
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}