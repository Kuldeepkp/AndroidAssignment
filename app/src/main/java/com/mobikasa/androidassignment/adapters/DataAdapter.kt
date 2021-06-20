package com.mobikasa.androidassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobikasa.androidassignment.databinding.ItemRecyclerBinding
import com.mobikasa.androidassignment.models.Restaurant
import com.mobikasa.androidassignment.models.RestaurantX

class DataAdapter(private var mAddDataToBookMark: (mData: RestaurantX) -> Unit) :
    PagingDataAdapter<Restaurant, DataAdapter.DataViewHolder>(DataDiff) {

    private object DataDiff : DiffUtil.ItemCallback<Restaurant>() {
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.restaurant?.id == newItem.restaurant?.id
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem == newItem
        }

    }


    inner class DataViewHolder(private var mBinding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(restaurantX: RestaurantX) {
            mBinding.restaurantName.text = restaurantX.name
            mBinding.averagePrice.text = restaurantX.currency.plus(restaurantX.averageCostForTwo)

            if (restaurantX.timings.isNullOrBlank()) {
                mBinding.timing.text = "Not Mentioned."
            } else {
                mBinding.timing.text = restaurantX.timings
            }
            mBinding.bookMarkImage.setOnClickListener {
                mAddDataToBookMark(restaurantX)
            }
        }
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item?.restaurant!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding =
            ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }
}