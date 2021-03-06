package com.example.minerva.util

import android.content.Context

class SecurityPreferences(context: Context) {
    private val mSharedPreferences =
        context.getSharedPreferences("minerva", Context.MODE_PRIVATE)

    fun storeString(key: String, value: String){
        mSharedPreferences.edit().putString(key, value).apply()

    }

    fun remove(key: String){
        mSharedPreferences.edit().remove(key)
    }

    fun getString(key: String): String{
        return mSharedPreferences.getString(key,"") ?: ""
    }
}