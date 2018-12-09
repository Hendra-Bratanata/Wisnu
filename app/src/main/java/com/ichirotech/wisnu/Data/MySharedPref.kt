package com.ichirotech.wisnu.Data

import android.content.Context
import android.provider.ContactsContract

class MySharedPref(context: Context) {
    val NAMA_PREF = "MY PREF"
    val EMAIL = "Email"
    val PASS = "Pass"
    val USER = "User"
    val URL = "url"


    val pref = context.getSharedPreferences(NAMA_PREF, Context.MODE_PRIVATE)

    fun getEmail(): String? {
        return pref.getString(EMAIL, null)
    }

    fun getPass(): String? {
        return pref.getString(PASS, null)
    }

    fun setEmail(email: String) {
        val editor = pref.edit()
        editor.putString(EMAIL, email)
        editor.apply()
    }

    fun setPass(pass: String) {
        val editor = pref.edit()
        editor.putString(PASS, pass)
        editor.apply()
    }

    fun setUser(user: String) {
        val editor = pref.edit()
        editor.putString(USER, user)
        editor.apply()
    }

    fun getUser(): String? {
        return pref.getString(USER, null)
    }

    fun setUrl(url: String) {
        val editor = pref.edit()
        editor.putString(URL, url)
        editor.apply()
    }

    fun getUrl(): String? {
        return pref.getString(URL, null)
    }
}
