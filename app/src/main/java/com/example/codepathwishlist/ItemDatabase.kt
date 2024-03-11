package com.example.codepathwishlist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDAO

    companion object{

        @Volatile
        private var INSTANCE: ItemDatabase?=null

        fun getDatabase(context: Context):ItemDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDatabase::class.java,
                     "item_database"
                    ).allowMainThreadQueries().build()
                INSTANCE=instance
                instance

            }

        }
    }

}