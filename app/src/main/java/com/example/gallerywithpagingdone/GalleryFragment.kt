package com.example.gallerywithpagingdone


import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.gallerywithpagingdone.databinding.FragmentGalleryBinding

/**
 * A simple [Fragment] subclass.
 */
class GalleryFragment : Fragment() {
    private val galleryViewModel by activityViewModels<GalleryViewModel>()
    private lateinit var galleryBinding:FragmentGalleryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        galleryBinding = FragmentGalleryBinding.inflate(inflater, container, false)
        return galleryBinding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.swipeIndicator -> {
                galleryBinding.swipeLayoutGallery.isRefreshing = true
                Handler().postDelayed( {galleryViewModel.resetQuery() },1000)
            }

        }

        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        val galleryAdapter = GalleryAdapter(galleryViewModel)
        galleryBinding.recyclerView.apply {
            adapter = galleryAdapter
            layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        }
        galleryViewModel.pagedListLiveData.observe(viewLifecycleOwner, Observer {
            galleryAdapter.submitList(it)
        })
        galleryBinding.swipeLayoutGallery.setOnRefreshListener {
            galleryViewModel.resetQuery()
        }
        galleryViewModel.networkStatus.observe(viewLifecycleOwner, Observer {
            Log.d("hello", "onActivityCreated: $it")
            galleryAdapter.updateNetworkStatus(it)
            galleryBinding.swipeLayoutGallery.isRefreshing = it == NetworkStatus.INITIAL_LOADING
        })
    }

}
