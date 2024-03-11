package com.example.codepathwishlist

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity
class Item(

    var name: String,
    var url: String,
    var price: Double,
    @PrimaryKey (autoGenerate = true) val uid:Int=0) {



}