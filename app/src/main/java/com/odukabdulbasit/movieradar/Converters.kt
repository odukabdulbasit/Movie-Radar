package com.odukabdulbasit.movieradar

import androidx.room.TypeConverter

class Converters {
        @TypeConverter
        fun listToString(list: List<Int>?): String? {
            return list!!.joinToString(",",prefix =  "", postfix = "", limit = list.size, truncated = "")
        }

        @TypeConverter
        fun fromstringToList(string: String?): List<Int>? {
            val result: List<Int> = string!!.split(",").map { it.trim().toInt() }
            return result
        }
}