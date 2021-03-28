package com.example.truflatask.presentaion.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.truflatask.database.Repo
import com.example.truflatask.base.BaseViewModel
import com.example.truflatask.network.NetworkState

class RepoViewModel : BaseViewModel() {

    var itemPagedReviewList: LiveData<PagedList<Repo>>? = null

    private var liveDataSource: LiveData<PageKeyedDataSource<Int, Repo>>? = null

    var pagingNetworkState: LiveData<NetworkState>? = null


    fun getRepos() {
        val itemDataSourceFactory = RepoFactory()
        liveDataSource = itemDataSourceFactory.getDataSource()

        pagingNetworkState = Transformations.switchMap(
                itemDataSourceFactory.getMutableLiveData()
        ) { dataSource: ReposDataSource -> dataSource.getNetworkState() }

        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(20)
                .setPageSize(20).build()
        itemPagedReviewList = LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)
                .build()

    } // fun of getRepos
}