package com.example.gallerywithpagingdone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gallerywithpagingdone.databinding.PagerPhotoViewBinding
import uk.co.senab.photoview.PhotoView

class PagerPhotoListAdapter:ListAdapter<PhotoItem,PagerPhotoViewHolder>(DiffCallback) {

    object DiffCallback:DiffUtil.ItemCallback<PhotoItem>(){
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem.photoId == newItem.photoId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerPhotoViewHolder {
        PagerPhotoViewBinding.inflate(LayoutInflater.from(parent.context),parent,false).apply {
            return PagerPhotoViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: PagerPhotoViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(getItem(position).previewUrl)
            .placeholder(R.drawable.photo_placeholder)
            .into(holder.pagerPhoto)
    }

}


//class PagerPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
class PagerPhotoViewHolder(binding: PagerPhotoViewBinding) : RecyclerView.ViewHolder(binding.root){
    val pagerPhoto: PhotoView
    init {
        pagerPhoto=binding.pagerPhoto
    }
}