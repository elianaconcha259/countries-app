package com.example.countriesapp.data.datasource.local.db

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DBProvider @Inject constructor(@ApplicationContext context: Context) {

    val database: AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "country_database")
            .fallbackToDestructiveMigration()
            .build()
}
