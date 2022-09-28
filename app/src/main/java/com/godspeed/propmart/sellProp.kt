package com.godspeed.propmart

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.godspeed.propmart.databinding.ActivitySellPropBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class sellProp : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var binding: ActivitySellPropBinding
    var naPlot = false
    private val db = Firebase.firestore
    private val auth = FirebaseAuth.getInstance()
    val newPlot:HashMap<String, Any> = HashMap()
    var i=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellPropBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectplotCat: ArrayList<String> = ArrayList()
//        selectplotCat.add("Select Property Category")
        selectplotCat.add("Agricultural Land")
        selectplotCat.add("NA Plot")
        selectplotCat.add("Guntha Plot")

        val selectsubplotCat: ArrayList<String> = ArrayList()
//        selectsubplotCat.add("Select Sub-Property Category")
        selectsubplotCat.add("Residential Land")
        selectsubplotCat.add("Commercial Land")
        selectsubplotCat.add("Residential cum Commercial Land")

        var area: ArrayList<String> = ArrayList()
        area.add("Sq Ft")
        area.add("H.R ")
        area.add("Sq Mt")
        area.add("Acres")

        var fr: ArrayList<String> = ArrayList()
        fr.add("Ft")
        fr.add("Mt")

        binding.frSpinner.onItemSelectedListener = this
        var adap0 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fr)
        adap0.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
        binding.frSpinner.adapter = adap0

        binding.areaSpinner.onItemSelectedListener = this
        var adap1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, area)
        adap1.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
        binding.areaSpinner.adapter = adap1

        binding.plotSpinner.onItemSelectedListener = this
        var adap2 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, selectplotCat)
        adap2.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
        binding.plotSpinner.adapter = adap2

        binding.subProprSpinner.onItemSelectedListener = this
        var adap3 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, selectsubplotCat)
        adap3.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
        binding.subProprSpinner.adapter = adap3

        binding.back1.setOnClickListener {
            binding.CardView2.visibility = GONE
            binding.CardView1.visibility = VISIBLE
        }

        binding.Next1.setOnClickListener {
            var fl1=0
            var fl2=0
            var fl3=0
            var fl4=0
            if(binding.ownerName.text.toString().isEmpty())
            {
                binding.ownerName.error = "Required"
            }
            else
            {
                fl1=1
                newPlot["Owner Name"] = binding.ownerName.text.toString()
            }
            if(binding.District.text.toString().isEmpty())
            {
                binding.District.error = "Required"
            }
            else
            {
                fl2=1
                newPlot["District"] = binding.District.text.toString()
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
            if(fl1==1 && fl2==1 && fl3==1 && fl4==1)
            {
                binding.CardView2.visibility = VISIBLE
                binding.CardView1.visibility = GONE
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
                newPlot["Survey No."] = binding.surveyNo.text.toString()
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

            newPlot["Bid Price"] = binding.bidAmt.text.toString()
//            db.collection("Plots/").add(newPlot).addOnSuccessListener{ docRef ->
//                        val docId = docRef.id;
//                        binding.llSell.visibility = GONE
//                        binding.completeSell.visibility = VISIBLE
//                        Toast.makeText(this, "Plot Successfully Added !! ", Toast.LENGTH_SHORT).show()
//                        Log.d("TEAFSF", docId);
//
//                }

            var fl1=0
            if(binding.bidAmt.text.toString().isEmpty())
            {
                binding.bidAmt.error = "Required"
            }
            else
            {
                fl1=1
            }
            if(fl1==1)
            {
                newPlot["Bid Price"] = binding.bidAmt.text.toString()
                newPlot["Uid"] = auth.currentUser?.uid.toString()
                db.collection("Plots").document()
                    .set(newPlot).addOnCompleteListener{task->
                        if (task.isSuccessful){
                            binding.llSell.visibility = GONE
                            binding.completeSell.visibility = VISIBLE
                            Toast.makeText(this, "Plot Successfully Added !! ", Toast.LENGTH_SHORT).show()
                        } else {
                            Log.d(ContentValues.TAG, "Error saving Plot! ", task.exception)
                            Toast.makeText(applicationContext, "Something went wrong!!", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }

        binding.visithomeSeller.setOnClickListener {
            val intent = Intent(this, BottomnavSeller::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val item = p0!!.selectedItem.toString()
//        Toast.makeText(this, "Item Selected $item", Toast.LENGTH_SHORT).show()
        if(item == "NA Plot" || (item=="Residential Land" || item=="Commercial Land" || item=="Residential cum Commercial Land"))
        {
            val plot = "NA Plot"
            var subPlot = ""
            newPlot["Property Category"] = plot.toString()
            when (item) {
                "Residential Land" -> {
                    subPlot = "Residential Land"
                }
                "Commercial Land" -> {
                    subPlot = "Commercial Land"
                }
                "Residential cum Commercial Land" -> {
                    subPlot = "Residential cum Commercial Land"
                }
            }
            newPlot["Sub-Property Category"] = subPlot.toString()
            binding.subProprSpinner.visibility = VISIBLE
            binding.textView13.visibility = VISIBLE
        }
        else
        {
            if(item=="Agricultural Land")
            {
                newPlot["Property Category"] = "Agricultural Land"
            }
            else if(item=="Guntha Plot")
            {
                newPlot["Property Category"] = "Guntha Plot"
            }
            binding.subProprSpinner.visibility = GONE
            binding.textView13.visibility = GONE
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}