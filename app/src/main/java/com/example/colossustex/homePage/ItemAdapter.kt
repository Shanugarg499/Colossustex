package com.example.colossustex.homePage

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.squareup.picasso.Picasso
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.example.colossustex.R
import com.smarteist.autoimageslider.DefaultSliderView
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderLayout
import kotlinx.coroutines.internal.artificialFrame


class ItemAdapter(options: FirebaseRecyclerOptions<Item>) :
    FirebaseRecyclerAdapter<Item, ItemAdapter.ItemViewHolder>(options) {
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

        holder.constraintLayout.setOnClickListener {
            when (position) {
                0 -> holder.constraintLayout.findNavController().navigate(HomePageDirections.actionHomePageToSpinningMillOfIndia())
                else -> Toast.makeText(it.context, model.description, Toast.LENGTH_SHORT).show()
            }
        }

        if (position == 0) {
            holder.sliderLayout.visibility = View.VISIBLE
        }                //To set the sliderLayout only for the first item
        else {
            holder.sliderLayout.visibility = View.GONE
        }


    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.imageView_image)
        val heading: TextView = itemView.findViewById(R.id.textView_heading)
        val description: TextView = itemView.findViewById(R.id.textView_description)
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout1)
        val sliderLayout: SliderLayout = itemView.findViewById(R.id.imageSlider)

        init {

            Log.i("Adapter", "visible")
            sliderLayout.visibility = View.VISIBLE
            sliderLayout.setIndicatorAnimation(IndicatorAnimations.SWAP) //set indicator animation by using 	 				SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
            sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            sliderLayout.scrollTimeInSec = 2; //set scroll delay in seconds :

            for (i in 0..3) {


                val sliderView = DefaultSliderView(constraintLayout.context)

                when (i) {
                    0 -> sliderView.imageUrl =
                        "https://images.pexels.com/photos/547114/pexels-photo-547114.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    1 -> sliderView.imageUrl =
                        "https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    2 -> sliderView.imageUrl =
                        "https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260"
                    3 -> sliderView.imageUrl =
                        "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                }

                sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                sliderView.setOnSliderClickListener {
                    Toast.makeText(
                        constraintLayout.context,
                        "This is slider " + (i + 1),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                //at last add this view in your layout :
                sliderLayout.addSliderView(sliderView)
            }

        }


    }


}


