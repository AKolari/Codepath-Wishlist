package com.example.codepathwishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ItemAdapter(private var wishList: ArrayList<Item>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // TODO: Create member variables for any view that will be set
        // as you render a row.

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        val nameTextView: TextView;
        val urlTextView: TextView;
        val priceTextView: TextView

        init {
            // TODO: Store each of the layout's views into
            // the public final member variables created above
            nameTextView=itemView.findViewById(R.id.itemName)
            urlTextView=itemView.findViewById(R.id.itemURL)
            priceTextView=itemView.findViewById(R.id.itemPrice)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val listItemView = inflater.inflate(R.layout.item_view, parent, false)
        // Return a new holder instance
        return ViewHolder(listItemView)
    }

    override fun getItemCount(): Int {
        return wishList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wishListItem=wishList.get(position)
        holder.nameTextView.text=wishListItem.name
        holder.urlTextView.text=wishListItem.url
        holder.priceTextView.text="$"+wishListItem.price.toString()

    }
}