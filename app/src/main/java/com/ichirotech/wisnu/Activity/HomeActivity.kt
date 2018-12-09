package com.ichirotech.wisnu.Activity

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import com.google.gson.Gson
import com.ichirotech.wisnu.Adapter.RecycleViewAdapter
import com.ichirotech.wisnu.ApiRepository
import com.ichirotech.wisnu.Data.Sensor
import com.ichirotech.wisnu.HomePresenter
import com.ichirotech.wisnu.R
import com.ichirotech.wisnu.Utility.invisible
import com.ichirotech.wisnu.Utility.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class HomeActivity : AppCompatActivity(),HomeView {
    override fun showLoading() {
       mProgressBar.visible()
    }

    override fun hideLoading() {
      mProgressBar.invisible()
    }

    override fun showData(data: List<Sensor>) {
        mSwipeRefreshLayout.isRefreshing = false
        this.data.clear()
        this.data.addAll(data)
        adapter.notifyDataSetChanged()
    }

    lateinit var mProgressBar : ProgressBar
    lateinit var mRecyclerView :RecyclerView
    lateinit var mSwipeRefreshLayout:SwipeRefreshLayout
    var data:MutableList<Sensor> = mutableListOf()
    lateinit var presenter : HomePresenter
    lateinit var adapter : RecycleViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeUI().setContentView(this)
        adapter = RecycleViewAdapter(this,data){
            Toast.makeText(this,"Yang anda pilih adalah ${it.nama}",Toast.LENGTH_LONG).show()
            startActivity<DetailActivity>("data" to it)
        }
        mRecyclerView.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()

        presenter = HomePresenter(this,request,gson)
        mSwipeRefreshLayout.onRefresh {
            presenter.getData(this)
        }
    presenter.getData(this)
    }

   inner class HomeUI:AnkoComponent<HomeActivity> {
        override fun createView(ui: AnkoContext<HomeActivity>)= with(ui){

            linearLayout {
                lparams(matchParent, wrapContent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                rightPadding = dip(16)
                leftPadding = dip(16)

                mSwipeRefreshLayout = swipeRefreshLayout {
                    setColorSchemeResources(R.color.colorAccent,
                            android.R.color.holo_blue_light,
                            android.R.color.holo_green_light,
                            android.R.color.holo_red_light)

                    relativeLayout {
                        lparams(matchParent, wrapContent)

                        mRecyclerView = recyclerView {
                            lparams(matchParent, wrapContent)
                            layoutManager = LinearLayoutManager(context)
                        }

                mProgressBar = progressBar {

                }.lparams{
                    centerHorizontally()
                }
                    }
                }
                }
            }
        }


    }
interface HomeView{
    fun showLoading()
    fun hideLoading()
    fun showData(data:List<Sensor>)
}
