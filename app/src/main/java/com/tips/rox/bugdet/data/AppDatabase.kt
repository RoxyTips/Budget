package com.tips.rox.bugdet.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by rox on 09/12/2017
 */
@Database(entities = arrayOf(Depense::class, Categorie::class), version=2)
abstract class AppDatabase : RoomDatabase(){
    abstract fun depenseDao():DepenseDao;
}