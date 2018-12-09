package com.ichirotech.wisnu.Activity

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.ichirotech.wisnu.Data.MySharedPref
import com.ichirotech.wisnu.R
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {
    lateinit var signIn : Button
    lateinit var email:EditText
    lateinit var signUp: Button
    lateinit var forgotPass: Button
    lateinit var pass : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainUi().setContentView(this)
        val pref = MySharedPref(this)


        signIn = find(R.id.btnSingIn)
        signUp = find(R.id.btnSignUp)
        forgotPass = find(R.id.buttonForgot)
        email = find(R.id.edtEmail)
        pass = find(R.id.edtPass)

        if (pref.getUser()!= null){
            var emailAktif = pref.getEmail()
            email.setText(emailAktif.toString())
        }
        signIn.setOnClickListener{
            var kosong = false
            val cekEmail = email.text.toString()
            val cekPass = pass.text.toString()
            print("Email  = $cekEmail  pass = $cekPass")

            if(TextUtils.isEmpty(cekEmail)){
                email.setError("Tidak Boleh Kosong")
                kosong = true

            }
            if (TextUtils.isEmpty(cekPass)){
                pass.setError("Password tidak boleh kosong")
                kosong = true

            }

            if (!kosong){
                val emailAktif = pref.getEmail().toString()
                val passAktif = pref.getPass().toString()
                if (cekEmail.equals(emailAktif,true) && cekPass.equals(passAktif,true)){
                    startActivity<SettingActivity>()
                }
                else{
                    toast("user tidak ditemukan silahkan sign Up")
                }
            }



        }
        signUp.setOnClickListener {
            startActivity<SingUpActivity>()
        }
        forgotPass.setOnClickListener {
            startActivity<ForgotPassActivity>()
        }



    }

    class MainUi : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {


            relativeLayout {

                textView {
                    id = R.id.tvSingIn
                    text = resources.getString(R.string.sing_in_to_wisnu)
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
                    id = R.id.edtEmail
                    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                    hint = resources.getString(R.string.email)
                }.lparams(width = matchParent) {
                    centerVertically()
                }
                frameLayout {
                    id = R.id.frmLayout
                    editText {
                        id = R.id.edtPass
                        inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        hint = resources.getString(R.string.password)
                    }.lparams(width = matchParent)

                }.lparams(width = matchParent) {
                    below(R.id.edtEmail)
                }
                button {
                    id = R.id.btnSingIn
                    textSize = 16f
                    textColor = Color.WHITE
                    background = ContextCompat.getDrawable(ctx, R.drawable.tombol_bulat)
                    text = resources.getString(R.string.singIn)
                }.lparams(width = matchParent) {
                    below(R.id.frmLayout)
                    margin = dip(10)
                }
                linearLayout {
                    id = R.id.layoutSignUp
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER
                    textView {
                        text = resources.getString(R.string.SINGuP)
                        textColor = Color.BLACK
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(wrapContent){
                        rightMargin = dip(10)
                    }
                    button {
                        textColor = Color.WHITE
                        background = ContextCompat.getDrawable(ctx, R.drawable.tombol_bulat)
                        id = R.id.btnSignUp
                        text = resources.getString(R.string.sing_up)
                    }.lparams(width = dip(100), height = dip(40))
                }.lparams(width = matchParent) {
                    below(R.id.btnSingIn)
                    margin = dip(10)
                }
                button {
                    id = R.id.buttonForgot
                    textColor= Color.WHITE
                    background = ContextCompat.getDrawable(ctx, R.drawable.tombol_warning)
                    text = resources.getString(R.string.forgot_password)
                }.lparams(matchParent, wrapContent) {
                    below(R.id.layoutSignUp)
                    margin = dip(10)
                }
            }
        }
    }
}
