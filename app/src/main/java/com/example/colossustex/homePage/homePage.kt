package com.example.colossustex.homePage

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderLayout
import android.widget.Toast
import com.example.colossustex.R
import com.smarteist.autoimageslider.SliderView
import com.smarteist.autoimageslider.DefaultSliderView


class homePage : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ItemAdapter
    lateinit var sliderLayout: SliderLayout

    private lateinit var viewModel: HomePageViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val lay = inflater.inflate(R.layout.home_page_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(HomePageViewModel::class.java)

        recyclerView = lay.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        val options = FirebaseRecyclerOptions.Builder<Item>()
            .setQuery(FirebaseDatabase.getInstance().reference.child("Item"), Item::class.java)
            .build()
        adapter = ItemAdapter(options)
        recyclerView.adapter = adapter

        sliderLayout = lay.findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using 	 				SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderLayout.scrollTimeInSec = 2; //set scroll delay in seconds :

        setSliderViews();






        return lay
    }

    private fun setSliderViews() {
        for (i in 0..3) {

            val sliderView = DefaultSliderView(context)

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
                    context,
                    "This is slider " + (i + 1),
                    Toast.LENGTH_SHORT
                ).show()
            }

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView)
        }
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }



}
