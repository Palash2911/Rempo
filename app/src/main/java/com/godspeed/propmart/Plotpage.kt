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

        val layoutid = this.intent?.getStringExtra("Layout_Id")
        val plotid = "plot"+this.intent?.getStringExtra("Plot_Id")
        val totarea = findViewById<TextView>(R.id.area)
        val bidhead = findViewById<TextView>(R.id.plot_no)
        val rate = findViewById<TextView>(R.id.rate)
        val dim = findViewById<TextView>(R.id.dimensions)
        val wp = findViewById<TextView>(R.id.warningplot)
        val desc = findViewById<TextView>(R.id.description)
        val placebtn = findViewById<Button>(R.id.placebidbtn)
        val bidamt = findViewById<EditText>(R.id.bid_amount)

        db.collection("Layouts").document("sample_layout").collection("plots").document("plot1").get().addOnSuccessListener { snapshot ->
            bidhead.text = "Bid on Plot " + plotid.substring(4)
            totarea.text =  snapshot["totalArea"].toString() + "Sq.m"
            desc.text = snapshot["description"].toString()
            rate.text = "Rate : " + snapshot["rate"].toString() + " Rs./sq.m"
            dim.text = snapshot["dimension"].toString()
            wp.text = "Current rate at this site is" + snapshot["rate"].toString() + "₹/sq.m. Kindly place your bid by considering current property rate"
            Log.d("Plot", snapshot["totalArea"].toString())
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
                db.collection("Users").document(Firebase.auth.currentUser?.uid.toString())
                    .get().addOnSuccessListener{snapshot ->
                        profile["name"]=snapshot["Name"].toString()
                        profile["placeBid"]=bidamt.text.toString()
                        val current = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(
                            FormatStyle.MEDIUM))
                        profile["time"]=current
                        profile["uid"]=Firebase.auth.currentUser?.uid.toString()
                        db.collection("Layouts").document("sample_layout")
                            .collection("plots").document("plot1").collection("bids")
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