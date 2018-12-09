package com.ichirotech.wisnu

import android.content.Context
import com.ichirotech.wisnu.Data.MySharedPref

object IotDataBase{
    fun getData(contx:Context):String{
        val pref = MySharedPref(contx)

        return pref.getUrl().toString()
    }
}