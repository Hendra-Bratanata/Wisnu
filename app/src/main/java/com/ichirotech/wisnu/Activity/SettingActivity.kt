package com.ichirotech.wisnu.Activity

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.ichirotech.wisnu.Data.MySharedPref
import com.ichirotech.wisnu.R
import org.jetbrains.anko.*

class SettingActivity : AppCompatActivity(){
    lateinit var mButtonSend : Button
    lateinit var mEdtUrl : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainUi2().setContentView(this)
        mButtonSend = find(R.id.btnSend)
        mEdtUrl = find(R.id.edtgetway)

        mButtonSend.setOnClickListener {

            val url = mEdtUrl.text.toString()
            var kosong = false

            if (TextUtils.isEmpty(url)){
                mEdtUrl.setError("Wajib Di isi")
                kosong = true
            }
            if (!kosong){
                val pref = MySharedPref(this)
                pref.setUrl(url)
                startActivity<HomeActivity>()
                this.finish()
            }
        }

    }

    class MainUi2 : AnkoComponent<SettingActivity> {
        override fun createView(ui: AnkoContext<SettingActivity>) = with(ui) {


            relativeLayout {

                textView {
                    id = R.id.tvSingIn
                    text = resources.getString(R.string.sing_in_to_wisnu2)
                    textColor = Color.BLACK
                    textSize = 16f //sp
                }.lparams {
                    alignParentLeft()
                    alignParentTop()
                    marginStart = dip(106)
                    leftMargin = dip(106)
                }
                imageView {
                    id = R.id.imgWisnu
                    imageResource = R.drawable.wisnu
                }.lparams(width = dip(100), height = dip(100)) {
                    centerHorizontally()
                    below(R.id.tvSingIn)
                    topMargin = dip(25)
                }
                editText {
                    id = R.id.edtgetway
                    inputType = InputType.TYPE_TEXT_VARIATION_URI or InputType.TYPE_CLASS_TEXT
                    setText(context.getString(R.string.ichirogateaWay))
                }.lparams(width = matchParent) {
                    centerVertically()
                }
                button("send") {
                    id = R.id.btnSend
                    textColor=Color.WHITE
                    background=ContextCompat.getDrawable(ctx,R.drawable.tombol_bulat)
                }.lparams{
                    below(R.id.edtgetway)
                    centerHorizontally()
                }

            }
        }
    }

}