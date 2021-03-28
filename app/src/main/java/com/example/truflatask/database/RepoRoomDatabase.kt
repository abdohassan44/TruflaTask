package com.example.truflatask.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Repo::class], version = 1, exportSchema = false)

abstract class RepoRoomDatabase : RoomDatabase() {

    abstract fun userDao(): RepoDeo

}