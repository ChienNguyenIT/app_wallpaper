package com.khater.retromvvm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
 import com.khater.retromvvm.databinding.FragmentCategoriesBinding
import com.khater.retromvvm.model.domain.Category

import com.khater.retromvvm.recyclerView.CategoriesAdapter
import com.khater.retromvvm.recyclerView.CategoryInteractionListener
import com.khater.retromvvm.utils.ApiListCategory


class CategoriesFragment : Fragment() ,CategoryInteractionListener{
    private lateinit var binding: FragmentCategoriesBinding

    private lateinit var recyclerViewAdapter :CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        recyclerAdapter()

        return binding.root
    }

    private fun recyclerAdapter() {
        val layoutManager = GridLayoutManager(context, 2)
        recyclerViewAdapter = CategoriesAdapter(ApiListCategory.list,this)
        binding.categoriesRecyclerView.layoutManager = layoutManager
        binding.categoriesRecyclerView.adapter = recyclerViewAdapter
    }

    override fun onClickCategory(category: Category, view: View) {
        val action = MainFragmentDirections.actionTestFragmentToSpecificCategoryFragment(category.categoryName)
        Navigation.findNavController(view)
            .navigate(action)

    }


}