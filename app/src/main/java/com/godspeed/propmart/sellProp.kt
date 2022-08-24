package com.godspeed.propmart

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

        Log.d("Tag", selectplotCat.toString())
        binding.plotSpinner.onItemSelectedListener = this
        var adap = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, selectplotCat)
        adap.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
        binding.plotSpinner.adapter = adap

        binding.spinner2.onItemSelectedListener = this
        var adap2 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, selectsubplotCat)
        adap2.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
        binding.spinner2.adapter = adap2

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val item = p0!!.selectedItem.toString()
        Toast.makeText(this, "Item Selected $item", Toast.LENGTH_SHORT).show()
        if(item == "NA Plot" || (item=="Residential Land" || item=="Commercial Land" || item=="Residential cum Commercial Land"))
        {
            binding.spinner2.visibility = VISIBLE
            binding.textView13.visibility = VISIBLE
        }
        else
        {
            binding.spinner2.visibility = GONE
            binding.textView13.visibility = GONE
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}