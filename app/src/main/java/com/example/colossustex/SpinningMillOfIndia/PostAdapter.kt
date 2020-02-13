package com.example.colossustex.SpinningMillOfIndia

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.colossustex.R

class PostAdapter() : RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    private lateinit var context: Context
    private lateinit var posts: ArrayList<post>

    constructor(c: Context, p: ArrayList<post>) : this() {
        this.context = c
        this.posts = p
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.fragment_spinning_mill_in_india_recyler_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, pos: Int) {

        holder.date.text = posts[pos].date + ","
        holder.time.text = posts[pos].time
        holder.call.text = posts[pos].name.toString().slice(IntRange(0,11))+"..."



        when {
            posts[pos].type == "increase" -> {
                holder.description.text =
                    posts[pos].name.toString() + " has increased price of " + posts[pos].product + " by Rs. " + posts[pos].oldPrice + " to Rs. " + posts[pos].newPrice + " per kg(Ex-Mill)"
                holder.image.setImageResource(R.drawable.ic_arrow_upward)
            }
            posts[pos].type == "decrease" -> {
                holder.description.text =
                    posts[pos].name.toString() + " has decreased price of " + posts[pos].product + " by Rs. " + posts[pos].priceReduced + " to Rs. " + posts[pos].newPrice + " per kg(Ex-Mill)"
                holder.image.setImageResource(R.drawable.ic_arrow_downward)
            }
            else -> holder.description.text =
                posts[pos].name.toString() + " has added a new product " + posts[pos].product + " at " + posts[pos].newProductPrice + " per kg(Ex-Mill)"
        }

        holder.call.setOnClickListener {
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.fragment_spinning_mills_in_india_call_dialog)
            dialog.window!!.attributes.windowAnimations = R.style.DialogScale
            dialog.window!!.attributes.dimAmount = 0.9f
            val call = dialog.findViewById<LinearLayout>(R.id.constraintLayout_call_spinning_mills_in_india)
            dialog.show()
        }


    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val date = itemView.findViewById<TextView>(R.id.textView_date)
        val time = itemView.findViewById<TextView>(R.id.textView_time)
        val description = itemView.findViewById<TextView>(R.id.textView_description)
        val call = itemView.findViewById<TextView>(R.id.textView_call)
        val image = itemView.findViewById<ImageView>(R.id.imageView_status)

    }
}