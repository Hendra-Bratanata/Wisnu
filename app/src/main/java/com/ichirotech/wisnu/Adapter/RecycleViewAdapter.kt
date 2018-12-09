package com.ichirotech.wisnu.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ichirotech.wisnu.Data.Sensor
import com.ichirotech.wisnu.R
import org.jetbrains.anko.find

class RecycleViewAdapter (val context: Context,val item:List<Sensor>,val listener:(Sensor)-> Unit):
        RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_main,p0,false))
    }


    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(item[p1],listener)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id = view.find<TextView>(R.id.tvIdData)
        val nama = view.find<TextView>(R.id.tvNamaData)
        val data = view.find<TextView>(R.id.tvDataData)
        val satuan = view.find<TextView>(R.id.tvSatuan)


        fun bindItem(datas: Sensor, listener: (Sensor) -> Unit) {
            id.text = datas.id.toString()
            nama.text = datas.nama.toString()
            data.text = datas.data.toString()
            satuan.text = datas.satuan.toString()

            itemView.setOnClickListener {
                listener(datas)
            }

        }
    }
}

