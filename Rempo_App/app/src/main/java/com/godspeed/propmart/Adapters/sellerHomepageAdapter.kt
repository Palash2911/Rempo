package com.godspeed.propmart.Adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.EditPlotActivity
import com.godspeed.propmart.Models.sellerhomepageModel
import com.godspeed.propmart.Plotpage
import com.godspeed.propmart.PropertyPageActivity
import com.godspeed.propmart.databinding.SellerCardBinding

class sellerHomepageAdapter(val context: Context, val cards:List<sellerhomepageModel>):
    RecyclerView.Adapter<sellerHomepageAdapter.sellerCardViewHolder>() {

    class sellerCardViewHolder(val binding: SellerCardBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): sellerCardViewHolder {
        val binding = SellerCardBinding.inflate(LayoutInflater.from(context),parent,false);
        return sellerHomepageAdapter.sellerCardViewHolder(binding);
    }

    override fun onBindViewHolder(holder: sellerHomepageAdapter.sellerCardViewHolder, position: Int) {
        with(holder){
            with(cards[position]){
                binding.title.text = this.title
                binding.seller.text = this.seller
                binding.plotCount.text = this.plotnumber.toString()
                binding.sellerAddress.text = this.address
//               Glide.with(context).load(this.layoutImage).into(binding.plotImage);

                holder.itemView.setOnClickListener{
                    val intent: Intent = Intent(context, EditPlotActivity::class.java);
                    val extras = Bundle()
                    extras.putString("layoutId",this.layoutId);
                    extras.putString("plotId",this.plotId);
                    extras.putString("title",this.title);
                    intent.putExtras(extras)
                    context.startActivity(intent);
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }
}