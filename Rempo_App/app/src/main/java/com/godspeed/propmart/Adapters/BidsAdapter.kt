package com.godspeed.propmart.Adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Fragments.Bidsfrag
import com.godspeed.propmart.Models.Bidscardmodel
import com.godspeed.propmart.Plotpage
import com.godspeed.propmart.PropertyPageActivity
import com.godspeed.propmart.databinding.BidscardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class BidsAdapter(val context: Context, val cards:List<Bidscardmodel>):
    RecyclerView.Adapter<BidsAdapter.BidsCardViewHolder>() {

    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance();
    val auth: FirebaseAuth = FirebaseAuth.getInstance();

    class BidsCardViewHolder(val binding: BidscardBinding): RecyclerView.ViewHolder(binding.root){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BidsAdapter.BidsCardViewHolder {

        val binding = BidscardBinding.inflate(LayoutInflater.from(context),parent,false);
        return BidsAdapter.BidsCardViewHolder(binding);
    }

    override fun onBindViewHolder(holder: BidsAdapter.BidsCardViewHolder, position: Int) {
//        val card: Bidscardmodel = cards[position];

        with(holder){
            with(cards[position]){

                binding.title.text = this.title
                binding.plotnum.text = "Plot No: " + this.plotno.toString();
                binding.plotbid.text = this.bidamt;
                binding.seller.text = this.seller
//               Glide.with(context).load(this.layoutImage).into(binding.plotImage);

                binding.rebidbtn.setOnClickListener{
                    val intent = Intent(context, Plotpage::class.java)
                    if(layoutId!="Null")
                    {
                        intent.putExtra("layoutId",this.layoutId);
                        intent.putExtra("plotId", this.plotId)
//                        intent.putExtra("plotId","plot"+this.plotno);
                    }
                    else
                    {

                        intent.putExtra("layoutId",this.layoutId);
                        intent.putExtra("plotId", this.plotId)
                    }
                    context.startActivity(intent)
                }

                binding.viewdbtn.setOnClickListener{
                    val intent = Intent(context, PropertyPageActivity::class.java)
                    intent.putExtra("layoutId",this.layoutId);
                    intent.putExtra("title",this.title);
                    if(this.layoutId=="Null")
                    {
                        intent.putExtra("plotId", this.plotId)
                    }
                    context.startActivity(intent)
                }

                holder.itemView.setOnClickListener{
                    val intent: Intent = Intent(context, PropertyPageActivity::class.java);
                    intent.putExtra("layoutId",this.layoutId);
                    intent.putExtra("title",this.title);
                    if(this.layoutId=="Null")
                    {
                        intent.putExtra("plotId", this.plotId)
                    }
                    context.startActivity(intent);
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }
}