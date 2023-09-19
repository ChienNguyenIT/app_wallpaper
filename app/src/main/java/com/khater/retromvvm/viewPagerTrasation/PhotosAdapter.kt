package com.khater.retromvvm.viewPagerTrasation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.khater.retromvvm.R
import com.khater.retromvvm.databinding.ItemViewpagerBinding
import com.khater.retromvvm.model.domain.Data
import com.khater.retromvvm.recyclerView.ViewPagerInterface


class PhotosAdapter(
    private val photosList: List<Data>,
    private val listener: ViewPagerInterface,
    ) : RecyclerView.Adapter<PhotosAdapter.PhotosHolder>() {

    class PhotosHolder(val itemLayoutBinding: ItemViewpagerBinding) :
        RecyclerView.ViewHolder(itemLayoutBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosHolder {
        val itemLayoutBinding = DataBindingUtil.inflate<ItemViewpagerBinding>(
            LayoutInflater.from(parent.context), R.layout.item_viewpager, parent, false
        )
        return PhotosHolder(itemLayoutBinding)
    }


    override fun getItemCount() =
        photosList.size

    override fun onBindViewHolder(holder: PhotosHolder, position: Int) {


        val currentItem = photosList[position]
        holder.itemLayoutBinding.photo = currentItem

        listener.positionItem(currentItem, position)

        holder.itemView.setOnClickListener {
            listener.onClickCategory(currentItem, it, holder.itemLayoutBinding)
        }

    }
}