package com.khater.retromvvm.ui.fragments


import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.khater.retromvvm.recyclerView.RecyclerViewAdapter
import com.khater.retromvvm.ui.fragments.base.BaseFragment
import com.khater.retromvvm.viewModels.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment (
)  {

    private val viewModel: HomeViewModel by viewModels()

    override fun initViewModel() {
        lifecycleScope.launch {
            viewModel.homePage.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }


    override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)




}
