package com.ichirotech.wisnu.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ichirotech.wisnu.Data.Sensor
import com.ichirotech.wisnu.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
lateinit var sensor:Sensor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        sensor = intent.getParcelableExtra("data")
        var cetak = "id :${sensor.id} nama : ${sensor.nama} data: ${sensor.data} ${sensor.satuan}"
        tvDetail.text = cetak

    }
}
