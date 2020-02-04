package com.example.colossustex.SpinningMillOfIndia

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

import com.example.colossustex.R
import org.w3c.dom.Text

class SpinningMillOfIndia : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var lay = inflater.inflate(R.layout.fragment_spinning_mill_of_india, container, false)
        val toolbar =
            lay.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_spinning_mills_in_india)
        val cotton = lay.findViewById<TextView>(R.id.textView_cotton)
        val synthetic = lay.findViewById<TextView>(R.id.textView_Synthetic)
        val viscose = lay.findViewById<TextView>(R.id.textView_viscose)
        val texturised = lay.findViewById<TextView>(R.id.textView_texturised)
        val fancy = lay.findViewById<TextView>(R.id.textView_fancy)
        val postYarnRequirement = lay.findViewById<CardView>(R.id.cardView_post_yarn_requirement)
        val directMillAgentsandTraders =
            lay.findViewById<CardView>(R.id.cardView_direct_mill_agent_and_traders)



        return lay
    }

}
