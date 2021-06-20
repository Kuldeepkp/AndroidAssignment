package com.mobikasa.androidassignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobikasa.androidassignment.databinding.ItemRecyclerBinding
import com.mobikasa.androidassignment.db.BookMarkEntity

class BookMarksAdapter(var list: List<BookMarkEntity>) :
    RecyclerView.Adapter<BookMarksAdapter.ViewHolder>() {


    inner class ViewHolder(var recyclerBinding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(recyclerBinding.root) {

        fun bindData(mData: BookMarkEntity) {
            recyclerBinding.bookMarkImage.visibility = View.GONE
            recyclerBinding.timing.visibility = View.GONE
            recyclerBinding.averagePrice.text = mData.avg_price
            recyclerBinding.restaurantName.text = mData.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }
}