package com.example.colossustex.SpinningMillOfIndia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import com.example.colossustex.R
import com.google.android.material.appbar.AppBarLayout
import com.google.firebase.database.*

class SpinningMillOfIndia : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mDb: DatabaseReference
    private lateinit var posts: ArrayList<post>
    private lateinit var adapter: PostAdapter
    private lateinit var manager: LinearLayoutManager
    private var done = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var lay = inflater.inflate(R.layout.fragment_spinning_mill_of_india, container, false)
        val toolbar =
            lay.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_spinning_mills_in_india)
        val upButton  = lay.findViewById<ImageView>(R.id.upButtonSpinningMillsOfIndia)
                     val cotton = lay.findViewById<TextView>(R.id.textView_cotton)
                val synthetic = lay.findViewById<TextView>(R.id.textView_Synthetic)
                val viscose = lay.findViewById<TextView>(R.id.textView_viscose)
                val texturised = lay.findViewById<TextView>(R.id.textView_texturised)
                val fancy = lay.findViewById<TextView>(R.id.textView_fancy)
                val postYarnRequirement = lay.findViewById<CardView>(R.id.cardView_post_yarn_requirement)
                val directMillAgentsandTraders =
                    lay.findViewById<CardView>(R.id.cardView_direct_mill_agent_and_traders)

        upButton.setOnClickListener {
            it.findNavController().navigateUp()
        }




        toolbar.inflateMenu(R.menu.menu_spinning_mills_of_india)
        toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.home_page ->{
                    toolbar.findNavController().navigateUp()
                }
            }
            true
        }


        manager = LinearLayoutManager(context)
        recyclerView = lay.findViewById(R.id.recylerView_spinning_mills_of_india)
        recyclerView.layoutManager = manager

        recyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (!done && manager.findFirstVisibleItemPosition() >= 1 && newState == SCROLL_STATE_IDLE) {
                        Toast.makeText(context, "dommb", Toast.LENGTH_SHORT).show()
                        done = true
                    }
                    if(done &&  manager.findFirstVisibleItemPosition() == 0 && newState == SCROLL_STATE_IDLE){
                        done = false
                    }

                }
            }
        )







        posts = ArrayList()

        mDb = FirebaseDatabase.getInstance().reference.child("postsSpinningMillsOfIndia")

        mDb.addValueEventListener(
            object : ValueEventListener {

                override fun onCancelled(p0: DatabaseError) {}

                override fun onDataChange(data: DataSnapshot) {
                    posts.clear()
                    for (dataSnapshot in data.children) {
                        val p = dataSnapshot.getValue(post::class.java)
                        posts.add(p!!)
                    }
                    posts.reverse()
                    adapter = PostAdapter(context!!, posts)
                    recyclerView.adapter = adapter
                }
            }
        )


        return lay
    }


}
