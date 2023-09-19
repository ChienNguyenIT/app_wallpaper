package com.khater.retromvvm.recyclerView

import android.view.View
import com.khater.retromvvm.databinding.ItemViewpagerBinding
import com.khater.retromvvm.model.domain.Data

interface ViewPagerInterface{
    fun positionItem(data:Data, position:Int)
    fun onClickCategory(data : Data, view: View,binding: ItemViewpagerBinding)
}