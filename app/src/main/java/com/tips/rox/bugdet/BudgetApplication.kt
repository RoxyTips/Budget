package com.tips.rox.bugdet

import android.app.Application
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.tips.rox.bugdet.data.AppDatabase

/**
 * Created by rox on 23/12/2017
 */
class BudgetApplication : Application() {

    companion object {
        var db : AppDatabase ? = null
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, AppDatabase::class.java, "Budget").allowMainThreadQueries().build()
    }
}