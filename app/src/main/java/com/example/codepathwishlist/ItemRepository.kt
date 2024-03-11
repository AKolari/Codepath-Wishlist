package com.example.codepathwishlist

import androidx.annotation.WorkerThread
import androidx.room.Insert
import kotlinx.coroutines.flow.Flow

class ItemRepository(private val ItemDao: ItemDAO) {

    val allItems: Flow<List<Item>> =ItemDao.getAll()



    suspend fun InsertItem(item: Item){
        ItemDao.insert(item)

    }

}