package com.hackathon.dresstolast.model

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    /*@TypeConverter
    fun optionToString(answers: Answers): String = Gson().toJson(answers)

    @TypeConverter
    fun stringToOption(string: String): Answers = Gson().fromJson(string, Answers::class.java)*/

    @TypeConverter
    fun fromList(optionValues: List<Answers?>?): String? {
        if (optionValues == null) {
            return null
        }
        val type: Type = object : TypeToken<List<Answers?>?>() {}.type
        return Gson().toJson(optionValues, type)
    }

    @TypeConverter
    fun toList(optionValuesString: String?): List<Answers>? {
        if (optionValuesString == null) {
            return null
        }
        val type: Type = object : TypeToken<List<Answers?>?>() {}.type
        return Gson().fromJson(optionValuesString, type)
    }

    @TypeConverter
    fun fromListString(optionValues: ArrayList<String?>?): String? {
        if (optionValues == null) {
            return null
        }
        val type: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().toJson(optionValues, type)
    }

    @TypeConverter
    fun toListString(optionValuesString: String?): ArrayList<String>? {
        if (optionValuesString == null) {
            return null
        }
        // Room doesnot support typeconverter while updating individual column
        // Handled it explicitly using catch block
        return try {
            val type: Type = object : TypeToken<ArrayList<String?>?>() {}.type
            Gson().fromJson(optionValuesString, type)
        } catch (e: JsonSyntaxException) {
            arrayListOf(optionValuesString)
        }
    }
}