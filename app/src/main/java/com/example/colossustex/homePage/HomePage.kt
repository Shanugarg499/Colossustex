package com.example.colossustex.homePage


import android.app.Dialog
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.example.colossustex.R
import com.google.android.material.textfield.TextInputLayout


class HomePage : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private lateinit var mDialog: Dialog

    private lateinit var viewModel: HomePageViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val lay = inflater.inflate(R.layout.home_page_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(HomePageViewModel::class.java)

        mDialog = Dialog(context!!)             //Used for showing Dialog


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
            when (it.itemId) {
                R.id.notification_Settings -> notificationSetting()
                R.id.edit_profile -> modifyProfile()
                R.id.change_password -> Toast.makeText(
                    context,
                    "Change Password",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.spinning_mill -> Toast.makeText(
                    context,
                    "Spinning mill?",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.support -> Toast.makeText(context, "Support", Toast.LENGTH_SHORT).show()
                R.id.advertise_with_us -> Toast.makeText(
                    context,
                    "Advertise With Us",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.rate_this_app -> Toast.makeText(
                    context,
                    "Rate This App",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.share_app -> Toast.makeText(context, "Share App", Toast.LENGTH_SHORT).show()
            }
            true
        }



        return lay
    }


    private fun notificationSetting() {                     //Add on click handlers to all switches
        mDialog.setContentView(R.layout.home_page_notification_setting)
        val close = mDialog.findViewById<TextView>(R.id.closeButtonNotificationSetting)
        val switch1 = mDialog.findViewById<Switch>(R.id.switch1)
        val switch2 = mDialog.findViewById<Switch>(R.id.switch2)
        val switch3 = mDialog.findViewById<Switch>(R.id.switch3)
        val switch4 = mDialog.findViewById<Switch>(R.id.switch4)
        val switch5 = mDialog.findViewById<Switch>(R.id.switch5)
        val switch6 = mDialog.findViewById<Switch>(R.id.switch6)
        close.setOnClickListener {
            mDialog.dismiss()
        }
        switch1.setOnCheckedChangeListener { compoundButton, state ->
            Toast.makeText(
                context,
                "Notifications for Indian cotton prices: $state",
                Toast.LENGTH_SHORT
            ).show()
        }
        switch2.setOnCheckedChangeListener { compoundButton, state ->
            Toast.makeText(
                context,
                "Notifications for Indian domestic prices: $state",
                Toast.LENGTH_SHORT
            ).show()
        }
        switch3.setOnCheckedChangeListener { compoundButton, state ->
            Toast.makeText(
                context,
                "Notifications for Indian export yarn prices: $state",
                Toast.LENGTH_SHORT
            ).show()
        }
        switch4.setOnCheckedChangeListener { compoundButton, state ->
            Toast.makeText(
                context,
                "Notifications for Bangladeshi yarn prices: $state",
                Toast.LENGTH_SHORT
            ).show()
        }
        switch5.setOnCheckedChangeListener { compoundButton, state ->
            Toast.makeText(context, "Notifications with sound: $state", Toast.LENGTH_SHORT).show()
        }
        switch6.setOnCheckedChangeListener { compoundButton, state ->
            Toast.makeText(context, "Notifications with vibration: $state", Toast.LENGTH_SHORT)
                .show()
        }

        mDialog.show()
    }

    private fun modifyProfile() {//store values in temp variable on click and validate each value and navigate to next page
        mDialog.setContentView(R.layout.home_page_modify_profile_1)
        var tempCountry: String
        var tempMobile: String
        var tempName: String
        var tempEmail: String
        var tempCity: String

        val editText_country = mDialog.findViewById<TextInputLayout>(R.id.editText_country)
        val editText_mobile = mDialog.findViewById<TextInputLayout>(R.id.editText_mobile)
        val editText_name = mDialog.findViewById<TextInputLayout>(R.id.editText_name)
        val editText_email = mDialog.findViewById<TextInputLayout>(R.id.editText_Email)
        val editText_city = mDialog.findViewById<TextInputLayout>(R.id.editText_city)
        val button_next = mDialog.findViewById<Button>(R.id.button_next)




        mDialog.show()
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
