package com.khater.retromvvm.viewPagerTrasation

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import com.khater.retromvvm.databinding.FragmentViewPagerBinding
import com.khater.retromvvm.databinding.ItemViewpagerBinding
import com.khater.retromvvm.model.domain.Data
import com.khater.retromvvm.model.networking.API
import com.khater.retromvvm.recyclerView.ViewPagerInterface
import com.khater.retromvvm.utils.BlurHashDecoder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


class ViewPagerFragment : Fragment(), ViewPagerInterface {

    private lateinit var binding: FragmentViewPagerBinding

    private val args: ViewPagerFragmentArgs by navArgs()
    private lateinit var photosAdapter  :PhotosAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentViewPagerBinding.inflate(inflater)



        apiCall()

        return binding.root
    }



    private  fun apiCall() {

        val mFlow = flow {
            val data = API.apiService.getCategoryToViewPager(args.imageData[2]).data
            data[0].smallImageUrl = args.imageData[0]
            emit(data)
        }.flowOn(Dispatchers.IO)


        lifecycleScope.launch {
            mFlow.collect{
                binding.viewpager.apply {
                    adapter = PhotosAdapter(it, this@ViewPagerFragment)
                    offscreenPageLimit = 4
                    setPageTransformer(SliderTransformer(4))
                }
            }
        }

//         CoroutineScope(Dispatchers.IO).launch {
//            val data = API.apiService.getCategoryToViewPager(args.imageData[2]).data
//
//
//
//
//            CoroutineScope(Dispatchers.Main).launch {
//                data[0].smallImageUrl = args.imageData[0]
//                binding.viewpager.apply {
//                    adapter = PhotosAdapter(data, this@ViewPagerFragment)
//                    offscreenPageLimit = 4
//                    setPageTransformer(SliderTransformer(4))
//                }
//            }
//
//        }

    }


    override fun positionItem(data: Data, position: Int) {
        val bh = data.blurHash
        val blurHashAsDrawable = BlurHashDecoder.decode(bh)
      binding.backGroundRelative.background =
          BitmapDrawable(resources, blurHashAsDrawable)
    }

    override fun onClickCategory(data: Data, view: View, binding: ItemViewpagerBinding) {
        val imageData = arrayOf(
            data.smallImageUrl.toString(),
            data.blurHash.toString(),
            data.category.toString()
        )
        val extras =
            FragmentNavigatorExtras(binding.imageViewViewPager to "image")
        Navigation.findNavController(view).navigate(
            ViewPagerFragmentDirections.actionViewPagerFragmentToDownloadFragment(imageData),
          extras
        )
    }


}