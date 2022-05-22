package com.example.countriesapp.common

import android.content.Context
import android.content.SharedPreferences
import com.example.countriesapp.R

object PrefsHelper {

    val ACCESS_TOKEN = "ACCESS_TOKEN"

    fun customPreference(context: Context): SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.prefs_token), Context.MODE_PRIVATE)

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.accessToken
        get() = getString(ACCESS_TOKEN, "")
        set(value) {
            editMe {
                it.putString(ACCESS_TOKEN, value)
            }
        }

    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            editMe {
                it.clear()
            }
        }
}