package com.example.truflatask.database

import androidx.lifecycle.LiveData
import com.example.truflatask.database.DatabaseHelper
import com.example.truflatask.database.Repo
import com.example.truflatask.database.RepoRoomDatabase

class DatabaseHelperImpl(private val repoRoomDatabase: RepoRoomDatabase) : DatabaseHelper {

    override  fun getRepo(): LiveData<List<Repo>> = repoRoomDatabase.userDao().getRepos()

     override fun insertAllRepo(repoList: List<Repo>) = repoRoomDatabase.userDao().insert(repoList)

    override fun deleteAllRepo() = repoRoomDatabase.userDao().delete()

}