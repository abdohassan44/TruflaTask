package com.example.truflatask.database

import android.content.Context
import androidx.room.Room


object DatabaseBuilder {

    private var INSTANCE: RepoRoomDatabase? = null

    fun getInstance(context: Context): RepoRoomDatabase {
        if (INSTANCE == null) {
            synchronized(RepoRoomDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                    context.applicationContext,
                    RepoRoomDatabase::class.java,
                    "Repo"
            )
                    .allowMainThreadQueries()
                    .build()

}