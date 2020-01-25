package com.example.colossustex.homePage


import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.example.colossustex.R


class HomePage : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ItemAdapter

    private lateinit var viewModel: HomePageViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val lay = inflater.inflate(R.layout.home_page_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(HomePageViewModel::class.java)

        recyclerView = lay.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        val options = FirebaseRecyclerOptions.Builder<Item>()
            .setQuery(FirebaseDatabase.getInstance().reference.child("Item"), Item::class.java)
            .build()
        adapter = ItemAdapter(options)
        recyclerView.adapter = adapter

        val toolbar = lay.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.main_menu)
        toolbar.setOnMenuItemClickListener {
            var toastMessage = ""
            when (it.itemId) {
                R.id.notification_Settings -> toastMessage = "Notification Settings"
                R.id.edit_profile -> toastMessage = "Edit Profile"
                R.id.change_password -> toastMessage = "Change Password"
                R.id.spinning_mill -> toastMessage = "Spinning mill?"
                R.id.support -> toastMessage = "Support"
                R.id.advertise_with_us -> toastMessage = "Advertise With Us"
                R.id.rate_this_app -> toastMessage = "Rate This App"
                R.id.share_app -> toastMessage = "Share App"
            }
            Toast.makeText( context , toastMessage , Toast.LENGTH_SHORT).show()
            true
        }



        return lay
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
