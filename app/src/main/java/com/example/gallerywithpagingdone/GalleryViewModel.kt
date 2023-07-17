package com.example.gallerywithpagingdone

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import androidx.paging.toLiveData

class GalleryViewModel(private val application: Application) : AndroidViewModel(application) {
    private var factory = PixabayDataSourceFactory(application,null)
    var pagedListLiveData = factory.toLiveData(1)//包装成liveData
    //做个转换，通过一个数据源，观察另外一个数据源
    val networkStatus = Transformations.switchMap(factory.pixabayDataSource) {it.networkStatus}
    fun resetQuery() {
        factory.keyWord=arrayOf("cat", "dog", "car", "beauty", "phone", "computer", "flower", "animal","magic","code").random()
        pagedListLiveData.value?.dataSource?.invalidate()
    }
    fun doQuery(keyWord:String?) {
        factory.keyWord=keyWord
        pagedListLiveData.value?.dataSource?.invalidate()
    }
    fun retry() {
        factory.pixabayDataSource.value?.retry?.invoke()
    }
}