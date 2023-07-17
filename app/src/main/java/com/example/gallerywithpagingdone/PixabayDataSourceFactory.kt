package com.example.gallerywithpagingdone

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

class PixabayDataSourceFactory(private val context: Context, var keyWord:String?):DataSource.Factory<Int,PhotoItem>() {
    private val _pixabayDataSource = MutableLiveData<PixabayDataSource>()
    val pixabayDataSource : LiveData<PixabayDataSource> = _pixabayDataSource
    override fun create(): DataSource<Int, PhotoItem> {
        return PixabayDataSource(context,keyWord).also {
            _pixabayDataSource.postValue(it)}
    }
}