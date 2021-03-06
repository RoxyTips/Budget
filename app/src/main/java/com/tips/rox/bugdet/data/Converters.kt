package com.tips.rox.bugdet.data

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Created by rox on 10/12/2017
 */
class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}