package com.godspeed.propmart.Adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Adapters.PropertyCardAdapter.PropertyCardViewHolder
import com.godspeed.propmart.Bottomtab
import com.godspeed.propmart.Models.PropertyCardModel
import com.godspeed.propmart.PropertyPageActivity
import com.godspeed.propmart.databinding.FragmentHomepageBinding
import com.godspeed.propmart.databinding.PropertyCardBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class PropertyCardAdapter(val context:Context , val cards:List<PropertyCardModel>):
    RecyclerView.Adapter<PropertyCardViewHolder>() {

      val firestore: FirebaseFirestore = FirebaseFirestore.getInstance();
      val auth: FirebaseAuth = FirebaseAuth.getInstance();

    class PropertyCardViewHolder(val binding: PropertyCardBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyCardViewHolder {
        val binding = PropertyCardBinding.inflate(LayoutInflater.from(context),parent,false);
        return PropertyCardViewHolder(binding);
    }

    override fun onBindViewHolder(holder: PropertyCardViewHolder, position: Int) {
        val card:PropertyCardModel = cards[position];
       with(holder){
           with(cards[position]){
               if(this.plotId=="Null")
               {
                   binding.plotscntll.visibility = VISIBLE
                   binding.title.text = this.title
                   binding.plotcnt.text = this.totalPlots
                   binding.districtv.text = "District: ${this.address}"
                   binding.talkuatv.text = "Taluka: ${this.taluka}"
                   binding.seller.text = "By - ${this.seller}"
//               Glide.with(context).load(this.layoutImage).into(binding.plotImage);
                   firestore.collection("Users").document(Firebase.auth.currentUser?.uid.toString()).collection("saved")
                       .document("Layout_"+this.layoutId).get().addOnSuccessListener { task->
                           if(task.exists())
                           {
                               binding.save.visibility = View.GONE;
                               binding.saved.visibility = View.VISIBLE;
                           }
                           else
                           {
                               binding.save.visibility = View.VISIBLE;
                               binding.saved.visibility = View.GONE;
                           }
                       }.addOnFailureListener {
                           Snackbar.make(binding.root,"Something Went Wrong !",Snackbar.LENGTH_LONG).show();
                       }

                   binding.save.setOnClickListener{
                       firestore.collection("Users").document(Firebase.auth.currentUser?.uid.toString()).collection("saved")
                           .document("Layout_"+this.layoutId).set(card).addOnSuccessListener {
                               binding.save.visibility = View.GONE;
                               binding.saved.visibility = View.VISIBLE;
                               Toast.makeText(context, "Added to Bookmarks", Toast.LENGTH_SHORT).show();
                           }
                   }
                   binding.saved.setOnClickListener{
                       firestore.collection("Users").document(Firebase.auth.currentUser?.uid.toString()).collection("saved")
                           .document("Layout_"+this.layoutId).delete().addOnSuccessListener {
                               binding.saved.visibility = View.GONE;
                               binding.save.visibility = View.VISIBLE;
                               Toast.makeText(context, "Removed from Bookmarks", Toast.LENGTH_SHORT).show();
                           }
                   }
                   holder.itemView.setOnClickListener{
                       val intent= Intent(context,PropertyPageActivity::class.java);
                       val extras = Bundle()
                       Log.d("LID", this.layoutId)
                       extras.putString("layoutId", this.layoutId);
                       extras.putString("plotId", "Null");
                       extras.putString("title", this.title);
                       intent.putExtras(extras)
                       context.startActivity(intent);
                   }

                   binding.map.setOnClickListener{
                       val intent = Intent(Intent.ACTION_VIEW);
                       intent.data = Uri.parse("geo:"+this.latitude+","+this.longitude);
                       val chooser = Intent.createChooser(intent ,"Launch Map");
                       context.startActivity(chooser);
                   }
                   binding.share.setOnClickListener{
                       //Share Link Activity using Firebase Dynamic Links
                   }
               }
               else
               {
                   binding.plotscntll.visibility = GONE
                   binding.title.text = this.title
                   binding.districtv.text = "District: ${this.address}"
                   binding.talkuatv.text = "Taluka: ${this.taluka}"
                   binding.seller.text = "By - ${this.seller}"
//               Glide.with(context).load(this.layoutImage).into(binding.plotImage);
                   firestore.collection("Users").document(Firebase.auth.currentUser?.uid.toString()).collection("saved")
                       .document("Plot_"+this.plotId).get().addOnSuccessListener { task->
                           if(task.exists())
                           {
                               binding.save.visibility = View.GONE;
                               binding.saved.visibility = View.VISIBLE;
                           }
                           else
                           {
                               binding.save.visibility = View.VISIBLE;
                               binding.saved.visibility = View.GONE;
                           }
                       }.addOnFailureListener {
                           Snackbar.make(binding.root,"Something Went Wrong !",Snackbar.LENGTH_LONG).show();
                       }

                   binding.save.setOnClickListener{
                       firestore.collection("Users").document(Firebase.auth.currentUser?.uid.toString()).collection("saved")
                           .document("Plot_"+this.plotId).set(card).addOnSuccessListener {
                               binding.save.visibility = View.GONE;
                               binding.saved.visibility = View.VISIBLE;
                               Toast.makeText(context, "Added to Bookmarks", Toast.LENGTH_SHORT).show();
                           }
                   }
                   binding.saved.setOnClickListener{
                       firestore.collection("Users").document(Firebase.auth.currentUser?.uid.toString()).collection("saved")
                           .document("Plot_"+this.plotId).delete().addOnSuccessListener {
                               binding.saved.visibility = View.GONE;
                               binding.save.visibility = View.VISIBLE;
                               Toast.makeText(context, "Removed from Bookmarks", Toast.LENGTH_SHORT).show();
                           }
                   }
                   holder.itemView.setOnClickListener{
                       val intent:Intent = Intent(context,PropertyPageActivity::class.java);
                       val extras = Bundle()
                       extras.putString("plotId",this.plotId);
                       extras.putString("title",this.title);
                       extras.putString("layoutId","Null");
                       intent.putExtras(extras)
                       context.startActivity(intent);
                   }

                   binding.map.setOnClickListener{
                       val intent:Intent = Intent(Intent.ACTION_VIEW);
                       intent.data = Uri.parse("geo:"+this.latitude+","+this.longitude);
                       val chooser = Intent.createChooser(intent ,"Launch Map");
                       context.startActivity(chooser);
                   }
                   binding.share.setOnClickListener{
                       //Share Link Activity using Firebase Dynamic Links
                   }
               }
           }
       }
    }

    override fun getItemCount(): Int {
        return cards.size
    }
}