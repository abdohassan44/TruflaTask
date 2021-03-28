package com.example.truflatask.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RepoDeo {
    @Query("SELECT * FROM repo")
    fun getRepos(): LiveData<List<Repo>>

    @Insert
    fun insert(repoList: List<Repo>)

    @Query("DELETE FROM repo")
    fun delete()

}