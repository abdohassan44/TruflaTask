package com.example.truflatask.presentaion.ui

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.truflatask.database.Repo

class RepoFactory : DataSource.Factory<Int, Repo>() {

    private val mutableLiveData: MutableLiveData<ReposDataSource> = MutableLiveData()

    private val dataSource: MutableLiveData<PageKeyedDataSource<Int, Repo>> = MutableLiveData()

    override fun create(): DataSource<Int, Repo> {
        val itemDataSource = ReposDataSource()
        dataSource.postValue(itemDataSource)
        mutableLiveData.postValue(itemDataSource)

        return itemDataSource
    }

    fun getDataSource(): MutableLiveData<PageKeyedDataSource<Int, Repo>> {
        return dataSource
    }

    fun getMutableLiveData(): MutableLiveData<ReposDataSource> {
        return mutableLiveData
    }
}