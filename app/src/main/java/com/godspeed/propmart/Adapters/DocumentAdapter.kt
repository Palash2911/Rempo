package com.godspeed.propmart.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Models.DocumentModel
import com.godspeed.propmart.WebView
import com.godspeed.propmart.databinding.DocumentListItemBinding

class DocumentAdapter(val context: Context,val documents:ArrayList<DocumentModel>)
    : RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder>() {

    inner class DocumentViewHolder(val binding: DocumentListItemBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder {
        val binding = DocumentListItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return DocumentViewHolder(binding);
    }

    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) {
        val document:DocumentModel = documents[position];

        with(holder){
            this.binding.documentName.text = document.title;

            this.itemView.setOnClickListener{
                val intent:Intent = Intent(context,WebView::class.java);
                intent.putExtra("downloadUrl",document.downloadUrl);
                context.startActivity(intent);
            }
        }
    }

    override fun getItemCount(): Int {
       return documents.size
    }


}