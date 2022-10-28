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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.godspeed.propmart.Models.DocumentModel
import com.godspeed.propmart.databinding.ActivityEditPlotBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*
import kotlin.collections.HashMap


class EditPlotActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var layoutId = ""
    lateinit var binding: ActivityEditPlotBinding
    lateinit var selectedPdf:Uri
    lateinit var firestore: FirebaseFirestore;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPlotBinding.inflate(layoutInflater);
        setContentView(binding.root)

        val intent = Intent()
        layoutId = intent.getStringExtra("layoutId").toString();
        Log.d("in", layoutId)
        firestore = FirebaseFirestore.getInstance();

        val title = intent.getStringExtra("title");

        binding.toolbarTitle.text = title;


        binding.editPlotDetails.setOnClickListener {
            binding.editDetailsPopup.visibility = View.VISIBLE;
        }

        var area: ArrayList<String> = ArrayList()
        area.add("Sq Ft")
        area.add("H.R ")
        area.add("Sq Mt")
        area.add("Acres")

        var bid: ArrayList<String> = ArrayList()
        bid.add("Sq Ft")
        bid.add("Sq Mt")
        bid.add("Acres")

        var fr: ArrayList<String> = ArrayList()
        fr.add("Ft")
        fr.add("Mt")

        binding.frSpinner.onItemSelectedListener = this
        var adap0 = ArrayAdapter<String>(this, R.layout.simple_spinner_item, fr)
        adap0.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
        binding.frSpinner.adapter = adap0

        binding.areaSpinner.onItemSelectedListener = this
        var adap1 = ArrayAdapter<String>(this, R.layout.simple_spinner_item, area)
        adap1.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
        binding.areaSpinner.adapter = adap1





        binding.upload.setOnClickListener {
            val pdfIntent:Intent = Intent();
            pdfIntent.setAction(Intent.ACTION_GET_CONTENT);
            pdfIntent.setType("application/pdf");
            startActivityForResult(pdfIntent,25);
        }

        binding.editScheme.setOnClickListener{it ->
            binding.editSchemePopup.visibility = View.VISIBLE;
        }

        binding.cancel.setOnClickListener{
            binding.editSchemePopup.visibility = View.GONE
        }

        binding.savePlot.setOnClickListener {

            val dialog:ProgressDialog = ProgressDialog(this);
            dialog.setCancelable(false);
            dialog.setMessage("Please Wait");
            dialog.show();

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
            if(fl1==1 && fl2==1 && fl3==1 && fl4==1 && fl5==1 && fl6==1) {
                val newPlot: HashMap<String, Any> = HashMap();
                newPlot["Survey No."] = binding.surveyNo.text.toString()
                newPlot["Location"] = binding.location.text.toString()
                newPlot["Plot No"] = binding.plotNo.text.toString()
                newPlot["Area"] = binding.aSize.text.toString()
                newPlot["Front"] = binding.fron.text.toString()
                newPlot["Depth"] = binding.dept.text.toString()
                newPlot["Road"] = binding.road.text.toString()

                firestore.collection("Layouts").document(layoutId + "")
                    .update(newPlot as Map<String, Any>).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Details Updated Successfully",
                                Toast.LENGTH_SHORT
                            );
                            binding.editSchemePopup.visibility = View.GONE;
                            dialog.dismiss();
                        }
                    }
            }

            dialog.dismiss();


        }

        binding.save.setOnClickListener {

         val dialog:ProgressDialog = ProgressDialog(this);
         dialog.setCancelable(false);
            dialog.setMessage("Please Wait");
            dialog.show();

         val owner:String = binding.ownerName.text.toString();
         val address:String = binding.address.text.toString();
         val district:String = binding.district.text.toString();
         val village:String = binding.editTextVillage.text.toString();
         val taluka:String = binding.editTextTaluka.text.toString();
//         val desc:String = binding.description.text.toString();
//         val type:String = binding.description.text.toString();

            val map:HashMap<String,String> = HashMap();

            map.set("sellerName",owner);
            map.set("district",district);
            map.set("address",address);
            map.set("village",village);
            map.set("taluka",taluka);
//          map.set("description",desc);
//          map.set("type",type);

            firestore.collection("Layouts").document(layoutId+"")
                .update(map as Map<String, Any>).addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Details Updated Successfully",Toast.LENGTH_SHORT);
                        binding.editSchemePopup.visibility = View.GONE;
                        dialog.dismiss();
                    }
                }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val dialog:ProgressDialog = ProgressDialog(this);
        val date:Date = Date();
        selectedPdf = data?.data!!

        if(selectedPdf!=null)
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

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}
