package com.example.tripmemories.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tripmemories.R
import kotlinx.android.synthetic.main.item_add_photos_adapter.view.*

class UserTripPhotosListAdapter(val context: Context, val tripImagesList: ArrayList<String>) :
    RecyclerView.Adapter<UserTripPhotosListAdapter.ViewHolder>() {

    class ViewHolder(val item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_add_photos_adapter, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return tripImagesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(tripImagesList[position])
            .into(holder.item.addPhotosView)

    }
}
