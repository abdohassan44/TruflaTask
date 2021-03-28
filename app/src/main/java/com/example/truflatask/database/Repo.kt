package com.example.truflatask.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Repo(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "name") val name: String?,
        @ColumnInfo(name = "description") val description: String?,
        @ColumnInfo(name = "watchers") val watchers: String?,
        @ColumnInfo(name = "url") val url: String?
)