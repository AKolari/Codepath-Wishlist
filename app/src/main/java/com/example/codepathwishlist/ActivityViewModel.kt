package com.example.codepathwishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ActivityViewModel(private val repo:ItemRepository):ViewModel() {

    val _items: LiveData<List<Item>> = repo.allItems.asLiveData()




    fun addItem(newItem:Item) = viewModelScope.launch {
        repo.InsertItem(newItem )
    }






}


class ItemModelFactory(private  val repo :ItemRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ActivityViewModel::class.java)){
            return ActivityViewModel(repo) as T
        }

        throw IllegalArgumentException("Invalid Type")
    }

}
