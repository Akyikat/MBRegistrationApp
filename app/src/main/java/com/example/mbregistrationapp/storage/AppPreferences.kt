package com.example.mbregistrationapp.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.mbregistrationapp.BuildConfig

object AppPreferences {

    private lateinit var preferences: SharedPreferences

    private const val PREFERENCE_NAME = "appPreferences"

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    private const val TOKEN: String = "token"
    private const val AUTH_TOKEN: String = "authToken"
    private const val USER_LOGIN: String = "userLogin"
    private const val REQUEST_ID: String = "requestId"

    var token: String?
        get() = preferences.getString(TOKEN, BuildConfig.TOKEN)
        set(value) {
            preferences.edit { putString(TOKEN, value) }
        }

    var authToken: String?
        get() = preferences.getString(AUTH_TOKEN, "")
        set(value) {
            preferences.edit { putString(AUTH_TOKEN, value) }
        }

    var login: String?
        get() = preferences.getString(USER_LOGIN, "")
        set(value) {
            preferences.edit { putString(USER_LOGIN, value) }
        }

    /**
     * request id from phone check request
     * */
    var requestId: String?
        get() = preferences.getString(REQUEST_ID, "")
        set(value) {
            preferences.edit { putString(REQUEST_ID, value) }
        }

}