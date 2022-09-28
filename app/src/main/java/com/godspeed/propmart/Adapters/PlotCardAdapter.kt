package com.godspeed.propmart.Adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Models.PlotCardModel
import com.godspeed.propmart.Models.PropertyCardModel
import com.godspeed.propmart.PropertyPageActivity
import com.godspeed.propmart.databinding.FragmentHomepageBinding
import com.godspeed.propmart.databinding.PropertyCardBinding
import com.godspeed.propmart.databinding.SellerCardBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class PlotCardAdapter(val context:Context , val cards:List<PlotCardModel>):
    RecyclerView.Adapter<PlotCardAdapter.PlotCardViewHolder>() {

    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance();
    val auth: FirebaseAuth = FirebaseAuth.getInstance();

    class PlotCardViewHolder(val binding: PropertyCardBinding):RecyclerView.ViewHolder(binding.root){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlotCardAdapter.PlotCardViewHolder {

        val binding = PropertyCardBinding.inflate(LayoutInflater.from(context),parent,false);
        return PlotCardAdapter.PlotCardViewHolder(binding);
    }

    override fun onBindViewHolder(holder: PlotCardAdapter.PlotCardViewHolder, position: Int) {
        val card:PlotCardModel = cards[position];
        with(holder){
            with(cards[position]){
                binding.title.text = this.title
                binding.address.text = this.address;
                binding.plotCount.text = this.plotNo.toString();
                binding.plottextview?.text  = "Plot No: "
//               Glide.with(context).load(this.layoutImage).into(binding.plotImage);

                binding.save.setOnClickListener{
                    firestore.collection("Users").document(Firebase.auth.currentUser?.uid.toString()).collection("saved")
                        .document(this.plotId.toString()).set(card).addOnSuccessListener {
                            binding.save.visibility = View.GONE;
                            binding.saved.visibility = View.VISIBLE;
                            Snackbar.make(binding.root,"Added to Bookmarks",Snackbar.LENGTH_LONG).show();
                        }
                }
                Log.d("CAAA2", holder.toString())
                binding.saved.setOnClickListener{
                    firestore.collection("Users").document(Firebase.auth.currentUser?.uid.toString()).collection("saved")
                        .document(this.plotId.toString()).delete().addOnSuccessListener {
                            binding.saved.visibility = View.GONE;
                            binding.save.visibility = View.VISIBLE;
                            Snackbar.make(binding.root,"Removed from Bookmarks, Visit Homepage to Update",Snackbar.LENGTH_LONG).show();
                        }
                }
                Log.d("CAAA3", holder.toString())
                holder.itemView.setOnClickListener{
                    val intent:Intent = Intent(context,PropertyPageActivity::class.java);
                    intent.putExtra("layoutId",this.plotId);
                    intent.putExtra("title",this.title);
                    context.startActivity(intent);
                }

                binding.map.setOnClickListener{
                    val intent:Intent = Intent(Intent.ACTION_VIEW);
                    intent.data = Uri.parse("geo:"+this.latitude+","+this.longitude);
                    val chooser = Intent.createChooser(intent ,"Launch Map");
                    context.startActivity(chooser);
                }
                Log.d("CAAA4", holder.toString())
                binding.share.setOnClickListener{
                    //Share Link Activity using Firebase Dynamic Links
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }
}