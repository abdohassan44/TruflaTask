package com.example.truflatask.application

import androidx.multidex.MultiDexApplication
import com.example.truflatask.database.DatabaseBuilder
import com.example.truflatask.database.DatabaseHelperImpl

class MyApp : MultiDexApplication() {


companion object
{
    lateinit var dbHelper:DatabaseHelperImpl
}
    override fun onCreate() {
        super.onCreate()

         dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(context = applicationContext))

    } // fun of onCreate

     // fun of provideDatabase
}