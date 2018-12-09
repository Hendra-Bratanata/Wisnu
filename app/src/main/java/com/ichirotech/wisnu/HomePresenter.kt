package com.ichirotech.wisnu

import android.content.Context
import com.google.gson.Gson
import com.ichirotech.wisnu.Activity.HomeView
import com.ichirotech.wisnu.Data.SensorResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class HomePresenter(val view:HomeView,
                    val apiRepository: ApiRepository,
                    val gson: Gson){

    fun getData(contex:Context){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(IotDataBase.getData(contex)),
                    SensorResponse::class.java)

        uiThread {
            view.hideLoading()
            view.showData(data.datas)
        }
        }

    }
}