package com.godspeed.propmart.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Models.DocumentEditModel
import com.godspeed.propmart.R
import com.godspeed.propmart.WebView
import com.godspeed.propmart.databinding.DocumentEditLayoutBinding
import com.godspeed.propmart.databinding.DocumentListItemBinding
import com.google.api.Context
import java.util.*

class DocumentEditAdapter(val context:android.content.Context , val documentList:ArrayList<DocumentEditModel>):
RecyclerView.Adapter<DocumentEditAdapter.DocumentEditViewHolder>(){

inner class DocumentEditViewHolder(val binding:DocumentEditLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentEditViewHolder {

        val binding = DocumentEditLayoutBinding.inflate(LayoutInflater.from(context),parent,false);
        return DocumentEditViewHolder(binding);
    }

    override fun onBindViewHolder(holder: DocumentEditViewHolder, position: Int) {
        val document: DocumentEditModel = documentList[position]

        holder.binding.documentName.text = document.title;
        holder.binding.edit.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(context, holder.binding.edit);
            popupMenu.menuInflater.inflate(R.menu.document_edit_menu, popupMenu.menu);
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId){

                



                }
                true
            })
            popupMenu.show()
        }
    }

    override fun getItemCount(): Int {
        return documentList.size
    }
}
