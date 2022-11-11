package com.godspeed.propmart.Adapters

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Models.DocumentModel
import com.godspeed.propmart.WebView
import com.godspeed.propmart.databinding.DocumentListItemBinding
import com.rajat.pdfviewer.PdfViewerActivity

class DocumentAdapter(val context: Context,val documents:List<DocumentModel>)
    : RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder>() {

    class DocumentViewHolder(val binding: DocumentListItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder {
        val binding = DocumentListItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return DocumentViewHolder(binding);
    }

    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) {
        with(holder){
            with(documents[position]){
                Log.d("TATA", this.downloadUrl)
                binding.documentName.text = this.title;
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
            }
        }
    }

    override fun getItemCount(): Int {
       return documents.size
    }
}