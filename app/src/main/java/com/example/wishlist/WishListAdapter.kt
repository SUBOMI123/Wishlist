package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class WishListAdapter(private val Items: MutableList<Wishlist>): RecyclerView.Adapter<WishListAdapter.ViewHolder>(){
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Your holder should contain a member variable for any view that will be set as you render
        // a row
        val NameTextView: TextView
        val NumberTextView: TextView
        val URLTextView: TextView

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            NameTextView = itemView.findViewById(R.id.NameTV)
            NumberTextView = itemView.findViewById(R.id.NumberTV)
            URLTextView = itemView.findViewById(R.id.urlTV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.single_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return Items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val Item = Items.get(position)
        // Set item views based on views and data model
        holder.NameTextView.text = Item.name
        holder.NumberTextView.text = Item.price.toString()
        holder.URLTextView.text = Item.url

        holder.itemView.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Item.url))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL for " + Item.name, Toast.LENGTH_LONG).show()
            }
        }

        holder.itemView.setOnLongClickListener {
          Items.removeAt(holder.adapterPosition)
          this.notifyDataSetChanged()
          true
        }
    }
}