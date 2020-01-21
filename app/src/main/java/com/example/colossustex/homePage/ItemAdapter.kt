package com.example.colossustex.homePage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderLayout
import com.squareup.picasso.Picasso
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.example.colossustex.MainActivity
import com.example.colossustex.R
import com.smarteist.autoimageslider.DefaultSliderView
import java.security.AccessController.getContext


class ItemAdapter(options: FirebaseRecyclerOptions<Item>) : FirebaseRecyclerAdapter<Item, ItemAdapter.ItemViewHolder>(options) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_page_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int, model: Item) {
        holder.description.text = model.description
        holder.heading.text = model.heading
        Picasso.get().load(model.image).into(holder.image)
        holder.constraintLayout.setOnClickListener{
            Toast.makeText(it.context,"position:${position+1}",Toast.LENGTH_SHORT).show()
        }
    }


    class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val image: ImageView = itemView.findViewById(R.id.imageView_image)
        val heading: TextView = itemView.findViewById(R.id.textView_heading)
        val description: TextView = itemView.findViewById(R.id.textView_description)
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
    }
}