package com.example.codepathwishlist

import android.R.attr.data
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.jetbrains.annotations.Async
import java.util.UUID


class MainActivity : AppCompatActivity() {

    private val database by lazy { ItemDatabase.getDatabase(this) }
    val repo by lazy { ItemRepository(database.itemDao()) }

    private val activityViewModel: ActivityViewModel by viewModels{ ItemModelFactory(repo) }


    lateinit var wishList: List<Item>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            wishList= activityViewModel._items.value.orEmpty()













        //Get input fields
        val nameInput = findViewById<EditText>(R.id.editName)
        val urlInput = findViewById<EditText>(R.id.editURL)
        val priceInput = findViewById<EditText>(R.id.editPrice)
        val submitButton = findViewById<Button>(R.id.button)

        val wishListViews = arrayOf(nameInput, urlInput, priceInput)
        var index = 0;

        val inputFlags= arrayOf(false, false, false)


        for( i in wishListViews){

            i.addTextChangedListener(object:TextWatcherWithData(submitButton, i, inputFlags, index){} )

            index++;

        }


        //Get RecyclerView
        val itemRv=findViewById<RecyclerView>(R.id.itemRv)

        val wishListArray=ArrayList(wishList)

        //Get Adapter
        val adapter = ItemAdapter(wishListArray)

        itemRv.adapter=adapter

        //Set Layout Manager to position the items
        itemRv.layoutManager= LinearLayoutManager(this);

        submitButton.setOnClickListener{
            //wishList.add(Item( nameInput.text.toString(), urlInput.text.toString(), priceInput.text.toString().toDouble()))
            val newItem= Item( nameInput.text.toString(), urlInput.text.toString(), priceInput.text.toString().toDouble())
            wishListArray.add(newItem)
            AsyncTask.execute{
                database.itemDao().insert(newItem)
            }









            adapter.notifyDataSetChanged()

            for(i in wishListViews){
                i.setText("");
            }

            for(i in 0 until 3){
                inputFlags[i]=false
            }

            submitButton.isInvisible=true


        }

    }


   suspend fun AddDataAsync(newItem: Item, activityViewModel: ActivityViewModel){

        activityViewModel.addItem(newItem)
    }
}


abstract class TextWatcherWithData(val _button: Button, val _editText: EditText, val inputFlags: Array<Boolean>, val index: Int ) : TextWatcher {




    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {


        /* Toast.makeText(this@MainActivity, "EditText 1", Toast.LENGTH_LONG).show()*/
        if(_editText.text.isNotEmpty()){
            inputFlags[index]=true;
            if(inputFlags[0]&&inputFlags[1]&&inputFlags[2]){
                _button.isVisible=true;
            }

        }
        else{
            inputFlags[index]=false;
            _button.isInvisible=true;
        }




    }

    override fun afterTextChanged(s: Editable) {


    }





}