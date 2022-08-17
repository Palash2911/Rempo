package com.godspeed.propmart.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Models.Bidscardmodel
import com.godspeed.propmart.Models.sellerhomepageModel
import com.godspeed.propmart.databinding.BidscardBinding

class selllerHomepageAdapter(val context: Context, val cards:List<sellerhomepageModel>):
    RecyclerView.Adapter<selllerHomepageAdapter.sellerCardViewHolder>() {

    class sellerCardViewHolder(val binding: BidscardBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): sellerCardViewHolder {
        val binding = BidscardBinding.inflate(LayoutInflater.from(context),parent,false);
        return selllerHomepageAdapter.sellerCardViewHolder(binding);
    }

    override fun onBindViewHolder(holder: sellerCardViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return cards.size
    }
}