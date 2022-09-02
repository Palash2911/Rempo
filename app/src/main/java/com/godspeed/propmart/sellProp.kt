package com.godspeed.propmart

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

class sellProp : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var binding: ActivitySellPropBinding
    var naPlot = false
    var i=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellPropBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val selectplotCat: ArrayList<String> = ArrayList()
        selectplotCat.add("Agricultural Land")
        selectplotCat.add("NA Plot")
        selectplotCat.add("Guntha Plot")

        val selectsubplotCat: ArrayList<String> = ArrayList()
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
            binding.CardView2.visibility = VISIBLE
            binding.CardView1.visibility = GONE
        }

        binding.Next2.setOnClickListener {
            binding.CardView3.visibility = VISIBLE
            binding.CardView2.visibility = GONE
        }

        binding.back2.setOnClickListener {
            binding.CardView3.visibility = GONE
            binding.CardView2.visibility = VISIBLE
        }

        binding.Next3.setOnClickListener {
            binding.llSell.visibility = GONE
            binding.completeSell.visibility = VISIBLE
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
            binding.subProprSpinner.visibility = VISIBLE
            binding.textView13.visibility = VISIBLE
        }
        else
        {
            binding.subProprSpinner.visibility = GONE
            binding.textView13.visibility = GONE
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}