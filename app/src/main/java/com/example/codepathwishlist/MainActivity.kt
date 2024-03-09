package com.example.codepathwishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var wishList: ArrayList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wishList = ArrayList<Item>()


        //Get input fields
        val nameInput = findViewById<EditText>(R.id.editName)
        val urlInput = findViewById<EditText>(R.id.editURL)
        val priceInput = findViewById<EditText>(R.id.editPrice)
        val submitButton = findViewById<Button>(R.id.button)



        //Get RecyclerView
        val itemRv=findViewById<RecyclerView>(R.id.itemRv)

        //Get Adapter
        val adapter = ItemAdapter(wishList)

        itemRv.adapter=adapter

        //Set Layout Manager to position the items
        itemRv.layoutManager= LinearLayoutManager(this);

        submitButton.setOnClickListener{
            wishList.add(Item(nameInput.text.toString(), urlInput.text.toString(), priceInput.text.toString().toDouble()))
            adapter.notifyDataSetChanged()


        }

    }
}