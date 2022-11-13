package com.godspeed.propmart

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.godspeed.propmart.databinding.ActivityPlotpageBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class Plotpage : AppCompatActivity() {

    private lateinit var _binding: ActivityPlotpageBinding
    private val db = Firebase.firestore
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plotpage)

        _binding = ActivityPlotpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutid = this.intent?.getStringExtra("layoutId")
        var plotid = "plot"+this.intent?.getStringExtra("plotId")?.substring(4)
        val totarea = findViewById<TextView>(R.id.area)
        val bidhead = findViewById<TextView>(R.id.plot_no)
        val rate = findViewById<TextView>(R.id.rate)
        val dim = findViewById<TextView>(R.id.dimensions)
        val wp = findViewById<TextView>(R.id.warningplot)
        val desc = findViewById<TextView>(R.id.description)
        var plotno = ""
        val placebtn = findViewById<Button>(R.id.placebidbtn)
        val bidamt = findViewById<EditText>(R.id.bid_amount)
        if(layoutid!="Null")
        {
            db.collection("Layouts").document(layoutid.toString()).collection("plots")
                .document(plotid).get().addOnSuccessListener { snapshot ->
                    bidhead.text = "Bid on Plot " + snapshot["title"].toString().substring(4)
                    totarea.text =  snapshot["totalArea"].toString() + snapshot["Area_Un"].toString()
                    desc.text = snapshot["description"].toString()
                    rate.text = "Rate : " + snapshot["rate"].toString() + "Rs./" + snapshot["Bids_Un"].toString()
                    dim.text = snapshot["dimension"].toString()
                    wp.text = "Current rate at this site is" + snapshot["rate"].toString() + "₹/sq.m. Kindly place your bid by considering current property rate"
                }
        }
        else
        {
            plotid = this.intent?.getStringExtra("plotId").toString()
            Log.d("Plotsss", plotid)
            db.collection("Plots").document(plotid).get().addOnSuccessListener { snapshot ->
                    bidhead.text = "Bid on Plot " + snapshot["Plot No"].toString()
                    plotno = snapshot["Plot No"].toString()
                    totarea.text =  snapshot["Area"].toString() + " " +snapshot["Area_Un"].toString()
                    desc.text = snapshot["Road"].toString()
                    rate.text = "Rate : " + snapshot["Bid Price"].toString() + " Rs./" + snapshot["Bids_Un"].toString()
                    dim.text = "Dimensions : " + snapshot["Front"].toString() + " " + snapshot["Front_Un"].toString() + " x " + snapshot["Depth"].toString() + " " + snapshot["Front_Un"].toString()
                    wp.text = "Current rate at this site is " + snapshot["Bid Price"].toString() + "₹/${snapshot["Bids_Un"].toString()} Kindly place your bid by considering current property rate"
                }
        }
        if(desc.text.toString() == "null" || desc.text.toString().isEmpty())
        {
            desc.visibility = GONE
        }
        if(dim.text.toString() == "null" || dim.text.toString().isEmpty())
        {
            dim.visibility = GONE
        }

        placebtn.setOnClickListener {
            if(bidamt.text.isEmpty()|| (bidamt.text.toString()>="0" && bidamt.text.toString()<="100"))
            {
                Toast.makeText(this, "Enter Valid Bid", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val profile:HashMap<String, Any> = HashMap()
                val Bids:HashMap<String, Any> = HashMap()
                if(layoutid!="Null")
                {
                    db.collection("Users").document(Firebase.auth.currentUser?.uid.toString())
                        .get().addOnSuccessListener{snapshot ->
                            profile["name"]=snapshot["Name"].toString()
                            profile["placeBid"]=bidamt.text.toString()
                            val current = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(
                                FormatStyle.MEDIUM))
                            profile["time"]=current
                            profile["uid"]=Firebase.auth.currentUser?.uid.toString()
                            profile["Phone"] = snapshot["Phone"].toString()
                            Bids["bidAmount"]=bidamt.text.toString()
                            Bids["layoutId"]=layoutid.toString()
                            Bids["plotId"] = ""
                            Bids["plotNo"]=plotid.substring(4)
                            Bids["time"]=current
                            db.collection("Users").document(Firebase.auth.currentUser?.uid.toString())
                                .collection("bids").document(plotid.toString()).set(Bids).addOnCompleteListener {
                                    Toast.makeText(this, "Bid Placed", Toast.LENGTH_SHORT).show()
                                }

                            db.collection("Layouts").document(layoutid.toString())
                                .collection("plots").document(plotid).collection("bids")
                                .document(Firebase.auth.currentUser?.uid.toString()).set(profile).addOnCompleteListener { task->
                                    if(task.isSuccessful)
                                    {
                                        val intent = Intent(this, Bidplaced::class.java)
                                        startActivity(intent)
                                    }
                                }
                        }
                }
                else
                {
                    db.collection("Users").document(Firebase.auth.currentUser?.uid.toString())
                        .get().addOnSuccessListener{snapshot ->
                            profile["name"]=snapshot["Name"].toString()
                            profile["placeBid"]=bidamt.text.toString()
                            val current = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(
                                FormatStyle.MEDIUM))
                            profile["time"]=current
                            profile["uid"]=Firebase.auth.currentUser?.uid.toString()
                            profile["Phone"] = snapshot["Phone"].toString()
                            Bids["bidAmount"]=bidamt.text.toString()
                            Bids["layoutId"]= ""
                            Bids["plotId"] = plotid
                            Bids["plotNo"]=plotno
                            Bids["time"]=current
                            db.collection("Users").document(Firebase.auth.currentUser?.uid.toString())
                                .collection("bids").document(plotid).set(Bids).addOnCompleteListener {
                                    Toast.makeText(this, "Bid Placed", Toast.LENGTH_SHORT).show()
                                }

                            db.collection("Plots").document(plotid).collection("bids")
                                .document(Firebase.auth.currentUser?.uid.toString()).set(profile).addOnCompleteListener { task->
                                    if(task.isSuccessful)
                                    {
                                        val intent = Intent(this, Bidplaced::class.java)
                                        startActivity(intent)
                                    }
                                }
                        }
                }
            }
        }

    }
}