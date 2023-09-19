package com.khater.retromvvm.viewPagerTrasation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


@BindingAdapter("android:src")
fun bindImageView(view: ImageView, url: String?) {
    Glide.with(view).load(url).centerCrop().into(view)
}
