package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var Items: MutableList<Wishlist>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Items = ArrayList()
        // Lookup the RecyclerView in activity layout
        val NameRv = findViewById<RecyclerView>(R.id.ListRv)
        // Create adapter passing in the list
        val adapter = WishListAdapter(Items)
        // Attach the adapter to the RecyclerView to populate items
        NameRv.adapter = adapter
        // Set layout manager to position the items
        NameRv.layoutManager = LinearLayoutManager(this)

        val submitBtn = findViewById<Button>(R.id.button)
        submitBtn.setOnClickListener{
            val inputNameView = findViewById<EditText>(R.id.ItemName)
            val inputNumberView = findViewById<EditText>(R.id.ItemNumber)
            val inputURLView = findViewById<EditText>(R.id.Url)
            val newObj = Wishlist(inputNameView.text.toString(),inputNumberView.text.toString().toDouble(),inputURLView.text.toString())
            Items.add(newObj)
            inputNameView.setText("")
            inputNumberView.setText("")
            inputURLView.setText("")
            adapter.notifyDataSetChanged()
        }
    }
}