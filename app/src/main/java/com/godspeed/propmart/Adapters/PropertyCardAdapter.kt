package com.godspeed.propmart.Adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.propmart.Adapters.PropertyCardAdapter.PropertyCardViewHolder
import com.godspeed.propmart.Bottomtab
import com.godspeed.propmart.Models.PropertyCardModel
import com.godspeed.propmart.PropertyPageActivity
import com.godspeed.propmart.databinding.FragmentHomepageBinding
import com.godspeed.propmart.databinding.PropertyCardBinding
import com.google.android.material.snackbar.Snackbar
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
        val card:PropertyCardModel = cards.get(position);

       with(holder){
           with(cards[position]){
               val docRef = firestore
                   .collection("Users/" + Firebase.auth.currentUser?.uid.toString() + "/saved")
                   .get().addOnSuccessListener { snapshot->
                       if(snapshot.isEmpty)
                       {
                           binding.save.visibility = View.VISIBLE;
                           binding.saved.visibility = View.GONE;
                       }
                       else {
                           binding.saved.visibility = View.VISIBLE;
                           binding.save.visibility = View.GONE
                       }
                       Log.d("Layss",layoutId)
               }
               binding.title.text = this.title
               binding.address.text = this.address;
               binding.plotCount.text = this.totalPlots.toString();
//               Glide.with(context).load(this.layoutImage).into(binding.plotImage);

               binding.save.setOnClickListener{
                   firestore.collection("Users").document(Firebase.auth.currentUser?.uid.toString()).collection("saved")
                       .document(this.layoutId.toString()).set(card).addOnSuccessListener {
                           Log.d("Saving", "Daddy")
                           binding.save.visibility = View.GONE;
                           binding.saved.visibility = View.VISIBLE;
                           Snackbar.make(binding.root,"Added to Bookmarks",Snackbar.LENGTH_LONG).show();
                       }
               }

               binding.saved.setOnClickListener{
                   firestore.collection("Users").document(Firebase.auth.currentUser?.uid.toString()).collection("saved")
                       .document(this.layoutId.toString()).delete().addOnSuccessListener {
                           binding.saved.visibility = View.GONE;
                           binding.save.visibility = View.VISIBLE;
                           Snackbar.make(binding.root,"Removed from Bookmarks, Visit Homepage to Update",Snackbar.LENGTH_LONG).show();
                       }
               }

               holder.itemView.setOnClickListener{
                    val intent:Intent = Intent(context,PropertyPageActivity::class.java);
                    intent.putExtra("layoutId",this.layoutId);
                    intent.putExtra("title",this.title);
                    context.startActivity(intent);
               }

               binding.map.setOnClickListener{
                   val intent:Intent = Intent(Intent.ACTION_VIEW);
                   intent.setData(Uri.parse("geo:"+this.latitude+","+this.longitude));
                   val chooser = Intent.createChooser(intent ,"Launch Map");
                   context.startActivity(chooser);
               }

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