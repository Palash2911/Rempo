package com.godspeed.propmart.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.EditPlotActivity
import com.godspeed.propmart.Models.DocumentEditModel
import com.godspeed.propmart.R
import com.godspeed.propmart.WebView
import com.godspeed.propmart.databinding.DocumentEditLayoutBinding
import com.godspeed.propmart.databinding.DocumentListItemBinding
import com.google.api.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.rajat.pdfviewer.PdfViewerActivity
import java.util.*

class DocumentEditAdapter(val context:android.content.Context , val documentList:List<DocumentEditModel>):
RecyclerView.Adapter<DocumentEditAdapter.DocumentEditViewHolder>(){

    val db: FirebaseFirestore = FirebaseFirestore.getInstance();
    val auth: FirebaseAuth = FirebaseAuth.getInstance();

inner class DocumentEditViewHolder(val binding:DocumentEditLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentEditViewHolder {

        val binding = DocumentEditLayoutBinding.inflate(LayoutInflater.from(context),parent,false);
        return DocumentEditViewHolder(binding);
    }

    override fun onBindViewHolder(holder: DocumentEditViewHolder, position: Int) {
        with(holder)
        {
            with(documentList[position])
            {
                itemView.setOnClickListener{
                    context.startActivity(
                        // Use 'launchPdfFromPath' if you want to use assets file (enable "fromAssets" flag) / internal directory
                        PdfViewerActivity.launchPdfFromUrl(           //PdfViewerActivity.Companion.launchPdfFromUrl(..   :: incase of JAVA
                            context,
                            this.downloadUrl,                                // PDF URL in String format
                            this.title,                        // PDF Name/Title in String format
                            "",                  // If nothing specific, Put "" it will save to Downloads
                            true                 // This param is true by defualt.
                        )
                    )
                }
                binding.documentName.text = this.title;
                if(this.downloadUrl.isNotEmpty())
                {
                    binding.deletebtn.visibility = VISIBLE
                    binding.uploadbtn.visibility = GONE
                }
                else
                {
                    binding.uploadbtn.visibility = VISIBLE
                    binding.deletebtn.visibility = GONE
                }
                if(this.title == "7/12")
                {
                    binding.deletebtn.setOnClickListener {
                        binding.progressBar.visibility = VISIBLE
                        val storageref = FirebaseStorage.getInstance().getReference("Documents/" + auth.currentUser?.uid.toString() + "_doc1_" + this.plotNo)
                        storageref.delete().addOnSuccessListener {
                            db.collection("Plots").document(this.plotId).update("712", "").addOnSuccessListener {
                                Toast.makeText(context,"Deleted", Toast.LENGTH_SHORT).show()
                                binding.progressBar.visibility = GONE
                                binding.uploadbtn.visibility = VISIBLE
                                binding.deletebtn.visibility = GONE
                            }.addOnFailureListener {
                                    Toast.makeText(context,it.toString(), Toast.LENGTH_SHORT).show()
                                    binding.deletebtn.visibility = VISIBLE
                                    binding.progressBar.visibility = GONE
                                }
                        }.addOnFailureListener {
                            Toast.makeText(context,it.toString(), Toast.LENGTH_SHORT).show()
                            binding.deletebtn.visibility = VISIBLE
                            binding.progressBar.visibility = GONE
                        }
                    }

                    binding.uploadbtn.setOnClickListener {
                        val intent = Intent(context, EditPlotActivity::class.java)
                        intent.type = "application/pdf"
                        intent.action = Intent.ACTION_GET_CONTENT
//                        context.startActivityForResult(intent, 1);
                    }
                }
                if(this.title == "Nakasha")
                {
                    binding.deletebtn.setOnClickListener {
                        binding.progressBar.visibility = VISIBLE
                        val storageref = FirebaseStorage.getInstance().getReference("Documents/" + auth.currentUser?.uid.toString() + "_doc2_" + this.plotNo)
                        storageref.delete().addOnSuccessListener {
                            db.collection("Plots").document(this.plotId).update("Nakasha", "").addOnSuccessListener {
                                Toast.makeText(context,"Deleted", Toast.LENGTH_SHORT).show()
                                binding.progressBar.visibility = GONE
                                binding.uploadbtn.visibility = VISIBLE
                                binding.deletebtn.visibility = GONE
                            }.addOnFailureListener {
                                Toast.makeText(context,it.toString(), Toast.LENGTH_SHORT).show()
                                binding.deletebtn.visibility = VISIBLE
                                binding.progressBar.visibility = GONE
                            }
                        }.addOnFailureListener {
                            Toast.makeText(context,it.toString(), Toast.LENGTH_SHORT).show()
                            binding.deletebtn.visibility = VISIBLE
                            binding.progressBar.visibility = GONE
                        }
                    }
                }
                if(this.title == "NA Order")
                {
                    binding.deletebtn.setOnClickListener {
                        binding.progressBar.visibility = VISIBLE
                        val storageref = FirebaseStorage.getInstance().getReference("Documents/" + auth.currentUser?.uid.toString() + "_doc3_" + this.plotNo)
                        storageref.delete().addOnSuccessListener {
                            db.collection("Plots").document(this.plotId).update("NA Order", "").addOnSuccessListener {
                                Toast.makeText(context,"Deleted", Toast.LENGTH_SHORT).show()
                                binding.progressBar.visibility = GONE
                                binding.uploadbtn.visibility = VISIBLE
                                binding.deletebtn.visibility = GONE
                            }.addOnFailureListener {
                                Toast.makeText(context,it.toString(), Toast.LENGTH_SHORT).show()
                                binding.deletebtn.visibility = VISIBLE
                                binding.progressBar.visibility = GONE
                            }
                        }.addOnFailureListener {
                            Toast.makeText(context,it.toString(), Toast.LENGTH_SHORT).show()
                            binding.deletebtn.visibility = VISIBLE
                            binding.progressBar.visibility = GONE
                        }
                    }
                }
                if(this.title == "Other")
                {
                    binding.deletebtn.setOnClickListener {
                        binding.progressBar.visibility = VISIBLE
                        val storageref = FirebaseStorage.getInstance().getReference("Documents/" + auth.currentUser?.uid.toString() + "_doc4_" + this.plotNo)
                        storageref.delete().addOnSuccessListener {
                            db.collection("Plots").document(this.plotId).update("Other", "").addOnSuccessListener {
                                Toast.makeText(context,"Deleted", Toast.LENGTH_SHORT).show()
                                binding.progressBar.visibility = GONE
                                binding.uploadbtn.visibility = VISIBLE
                                binding.deletebtn.visibility = GONE
                            }.addOnFailureListener {
                                Toast.makeText(context,it.toString(), Toast.LENGTH_SHORT).show()
                                binding.deletebtn.visibility = VISIBLE
                                binding.progressBar.visibility = GONE
                            }
                        }.addOnFailureListener {
                            Toast.makeText(context,it.toString(), Toast.LENGTH_SHORT).show()
                            binding.deletebtn.visibility = VISIBLE
                            binding.progressBar.visibility = GONE
                        }
                    }
                }

            }
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(requestCode==1 && resultCode == AppCompatActivity.RESULT_OK)
//        {
//            binding.uploadbtn1.visibility = GONE
//            binding.progressBar4.visibility = VISIBLE
//            val storageref = FirebaseStorage.getInstance().getReference("Documents/" + auth.uid.toString() + "_doc1_" + newPlot["Plot No"])
//            storageref.putFile(data?.data!!).addOnSuccessListener {
//                storageref.downloadUrl.addOnSuccessListener{ uri ->
//                    val downloadUrl = uri.toString();
//                    val docs:HashMap<String, Any> = HashMap()
//                    newPlot["712"] = downloadUrl
//                    Toast.makeText(this , "File Uploaded Successfully", Toast.LENGTH_SHORT).show()
//                    binding.deletebtn1.visibility = VISIBLE
//                    binding.progressBar4.visibility = GONE
//                }
//            }
//        }
//        else if(requestCode==2 && resultCode == AppCompatActivity.RESULT_OK)
//        {
//            binding.uploadbtn2.visibility = GONE
//            binding.progressBar5.visibility = VISIBLE
//            val storageref = FirebaseStorage.getInstance().getReference("Documents/" + auth.uid.toString() + "_doc2_" + newPlot["Plot No"])
//            storageref.putFile(data?.data!!).addOnSuccessListener {
//                storageref.downloadUrl.addOnSuccessListener{ uri ->
//                    val downloadUrl = uri.toString();
//                    val docs:HashMap<String, Any> = HashMap()
//                    newPlot["Nakasha"] = downloadUrl
//                    Toast.makeText(this , "File Uploaded Successfully", Toast.LENGTH_SHORT).show()
//                    binding.deletebtn2.visibility = VISIBLE
//                    binding.progressBar5.visibility = GONE
//                }
//            }
//        }
//        else if(requestCode==3 && resultCode == AppCompatActivity.RESULT_OK)
//        {
//            binding.uploadbtn3.visibility = GONE
//            binding.progressBar6.visibility = VISIBLE
//            val storageref = FirebaseStorage.getInstance().getReference("Documents/" + auth.uid.toString() + "_doc3_" + newPlot["Plot No"])
//            storageref.putFile(data?.data!!).addOnSuccessListener {
//                storageref.downloadUrl.addOnSuccessListener{ uri ->
//                    val downloadUrl = uri.toString();
//                    val docs:HashMap<String, Any> = HashMap()
//                    newPlot["NA Order"] = downloadUrl
//                    Toast.makeText(this , "File Uploaded Successfully", Toast.LENGTH_SHORT).show()
//                    binding.deletebtn3.visibility = VISIBLE
//                    binding.progressBar6.visibility = GONE
//                }.addOnFailureListener {
//                    Toast.makeText(this, "Some Internal Error Occurred !", Toast.LENGTH_SHORT).show()
//                }
//            }.addOnFailureListener {
//                Toast.makeText(this, "Some Internal Error Occurred !", Toast.LENGTH_SHORT).show()
//            }
//        }
//        else if(requestCode==4 && resultCode == AppCompatActivity.RESULT_OK)
//        {
//            binding.uploadbtn4.visibility = GONE
//            binding.progressBar7.visibility = VISIBLE
//            val storageref = FirebaseStorage.getInstance().getReference("Documents/" + auth.uid.toString() + "_doc4_" + newPlot["Plot No"])
//            storageref.putFile(data?.data!!).addOnSuccessListener {
//                storageref.downloadUrl.addOnSuccessListener{ uri ->
//                    val downloadUrl = uri.toString();
//                    val docs:HashMap<String, Any> = HashMap()
//                    newPlot["Other"] = downloadUrl
//                    Toast.makeText(this , "File Uploaded Successfully", Toast.LENGTH_SHORT).show()
//                    binding.deletebtn4.visibility = VISIBLE
//                    binding.progressBar7.visibility = GONE
//                }.addOnFailureListener {
//                    Toast.makeText(this, "Some Internal Error Occurred !", Toast.LENGTH_SHORT).show()
//                }
//            }.addOnFailureListener {
//                Toast.makeText(this, "Some Internal Error Occurred !", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    override fun getItemCount(): Int {
        return documentList.size
    }
}
