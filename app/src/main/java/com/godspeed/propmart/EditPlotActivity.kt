package com.godspeed.propmart

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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


class EditPlotActivity : AppCompatActivity() {
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
//            map.set("description",desc);
//            map.set("type",type);

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
}