package com.nguyenvuluan.videomeetingapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class PreferenceManager constructor(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        Constants.KEY_PREFERENCE_NAME,
        Context.MODE_PRIVATE
    )

    @SuppressLint("CommitPrefEdits")
    fun putBoolean(key: String, value: Boolean) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    @SuppressLint("CommitPrefEdits")
    fun putString(key: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    @SuppressLint("CommitPrefEdits")
    fun clearPreferences() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}