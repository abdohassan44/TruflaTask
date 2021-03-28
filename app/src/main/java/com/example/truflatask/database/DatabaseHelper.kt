package com.example.truflatask.database

import androidx.lifecycle.LiveData
import com.example.truflatask.database.Repo

interface DatabaseHelper {
     fun getRepo(): LiveData<List<Repo>>

     fun insertAllRepo(users: List<Repo>)

     fun deleteAllRepo()

}