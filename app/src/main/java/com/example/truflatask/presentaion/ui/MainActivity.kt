package com.example.truflatask.presentaion.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.truflatask.R
import com.example.truflatask.database.Repo
import com.example.truflatask.network.NetworkState
import com.example.truflatask.presentaion.adapter.RepoAdapter
import com.example.truflatask.presentaion.custom.ProgressLoading
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private val viewModel : RepoViewModel by viewModels()

    private lateinit var adapter : RepoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initList()
        initViewModel()
    } // fun of onCreate

    private fun initList()
    {
        adapter = RepoAdapter(context = this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    } // fun of initList

    private fun initViewModel()
    {
        viewModel.getRepos()
        viewModel.networkState.observe(this,networkStateObserver)
        viewModel.pagingNetworkState?.observe(this,pagingNetworkStateObserver)
        viewModel.itemPagedReviewList?.observe(this,reposObserver)
    } // fun of initViewModel


    private val networkStateObserver = Observer<NetworkState> { networkState ->
        when(networkState.status)
        {
            NetworkState.Status.RUNNING -> {
                ProgressLoading.show(this)
            } // LOADING
            NetworkState.Status.SUCCESS -> {
                ProgressLoading.dismiss()
            }// LOADED
            NetworkState.Status.FAILED -> {
                ProgressLoading.dismiss()
                Toast.makeText(this,getString(R.string.something_wrong), Toast.LENGTH_SHORT).show()
            } // FAILED
        }
    } // networkStateObserver

    private val pagingNetworkStateObserver = Observer<NetworkState> { networkState ->
        adapter.setNetworkState(newNetworkState = networkState.status)
    } // networkStateObserver

    private val reposObserver = Observer<PagedList<Repo>>
    {
        adapter.submitList(it)
    } // reposObserver
}