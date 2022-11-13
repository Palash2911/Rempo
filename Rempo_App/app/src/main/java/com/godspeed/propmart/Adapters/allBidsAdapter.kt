package com.godspeed.propmart.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Models.allBidsModel
import com.godspeed.propmart.Plotpage
import com.godspeed.propmart.PropertyPageActivity
import com.godspeed.propmart.databinding.AllbidscardBinding

class allBidsAdapter(val context: Context, val cards:List<allBidsModel>):
    RecyclerView.Adapter<allBidsAdapter.allBidsCardViewHolder>() {
    class allBidsCardViewHolder(val binding: AllbidscardBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): allBidsCardViewHolder {
        val binding = AllbidscardBinding.inflate(LayoutInflater.from(context),parent,false);
        return allBidsCardViewHolder(binding);
    }

    override fun onBindViewHolder(holder: allBidsCardViewHolder, position: Int) {
        with(holder){
            with(cards[position]){
                binding.bidderName.text = this.Bidder
                binding.phone.text =  this.pNo;
                binding.bidPrice.text = this.Bid;
                binding.datebid.text = this.date;
//               Glide.with(context).load(this.layoutImage).into(binding.plotImage);
            }
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }
}