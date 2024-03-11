package com.example.codepathwishlist

import android.util.Log
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemDAO {
    @Query("SELECT * FROM item")
    fun getAll(): Flow<List<Item>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( item: Item)


    @Delete
    fun delete(item:Item)
}