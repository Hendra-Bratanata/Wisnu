package com.ichirotech.wisnu.Activity

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ichirotech.wisnu.Data.MySharedPref
import com.ichirotech.wisnu.R
import org.jetbrains.anko.*

class SingUpActivity : AppCompatActivity() {
    lateinit var register: Button
    lateinit var userName: EditText
    lateinit var email: EditText
    lateinit var pass1 : EditText
    lateinit var pass2 : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SingUpUI().setContentView(this)
        register = find(R.id.btnRegister)
        userName = find(R.id.edtUserName)
        email = find(R.id.edtEmailSignUp)
        pass1 = find(R.id.edtPassSingUp)
        pass2 = find(R.id.edtRePass)
        register.setOnClickListener {
            var kosong = false
            val mUserName = userName.text.toString()
            val mEmail = email.text.toString()
            val mPass1 = pass1.text.toString()
            val mPass2 = pass2.text.toString()

            //Cek field kosong atau tidak
            if (TextUtils.isEmpty(mUserName)){
                userName.setError("Wajib di isi")
                kosong = true
            }
            if (TextUtils.isEmpty(mEmail)){
                email.setError("Wajib di isi")
                kosong =true
            }
            if (TextUtils.isEmpty(mPass1)){
                pass1.setError("Wajib di isi")
                kosong = true
            }
            if (TextUtils.isEmpty(mPass2)){
                pass2.setError("Wajib di isi")
                kosong = true

            }
            if (!kosong){
                //validasi ke 2 cek kesamaan password
                if (mPass1 == mPass2){
                  val myPref = MySharedPref(this)
                    //isi data ke shared pref
                    myPref.setEmail(mEmail)
                    myPref.setPass(mPass1)
                    myPref.setUser(mUserName)
                    Toast.makeText(this,"Registrasi berhasil",Toast.LENGTH_LONG).show()
                    this.finish()
                    startActivity<MainActivity>()
                }else{
                    Toast.makeText(this,"Password tidak sama",Toast.LENGTH_SHORT).show()
                    pass2.setError("Harus sama")
                    pass1.setError("Harus sama")
                }
            }
        }
    }

    class SingUpUI: AnkoComponent<SingUpActivity> {
        override fun createView(ui: AnkoContext<SingUpActivity>)= with(ui) {
            relativeLayout {
                textView {
                    id = R.id.tvSignUp
                    text="SignUp"
                    textSize = 20f
                    textColor = Color.BLACK
                }.lparams{
                    centerHorizontally()
                    margin = dip(15)
                }
                imageView {
                    id = R.id.imgSignUp
                    imageResource = R.drawable.wisnu
                }.lparams(dip(100),dip(100)){
                    below(R.id.tvSignUp)
                    centerHorizontally()
                    bottomMargin = dip(20)
                }


                        textView {
                            id = R.id.tvUserName
                            textSize = 16f
                            text = context.getString(R.string.userName)
                            textColor = Color.BLACK

                        }.lparams(dip(100), wrapContent){
                            below(R.id.imgSignUp)
                            alignParentLeft()
                            bottomMargin = dip(16)
                            topMargin = dip(16)
                            leftMargin =dip(5)
                        }
                        textView {
                            id = R.id.tvEmaiSignUp
                            textSize = 16f
                            text = context.getString(R.string.emailSignUp)
                            textColor = Color.BLACK

                        }.lparams(dip(100), wrapContent){
                            below(R.id.tvUserName)
                            alignParentLeft()
                            bottomMargin = dip(16)
                            topMargin = dip(16)
                            leftMargin =dip(5)
                        }
                        textView {
                            id = R.id.tvPass
                            textSize = 16f
                            text = context.getString(R.string.pass)
                            textColor = Color.BLACK

                        }.lparams(dip(100), wrapContent){
                            below(R.id.tvEmaiSignUp)
                            alignParentLeft()
                            bottomMargin = dip(16)
                            topMargin = dip(16)
                            leftMargin =dip(5)
                        }
                        textView {
                            id = R.id.tvRePass
                            textSize = 16f
                            text =context.getString(R.string.rePass)
                            textColor = Color.BLACK

                        }.lparams(dip(100), wrapContent){
                            below(R.id.tvPass)
                            alignParentLeft()
                            bottomMargin = dip(16)
                            topMargin = dip(16)
                            leftMargin =dip(5)
                        }




                        editText {
                            id = R.id.edtUserName
                            textSize =  15f
                            hint = "Masukan username anda"
                        }.lparams(matchParent, wrapContent){
                            rightOf(R.id.tvUserName)
                            below(R.id.imgSignUp)
                            baselineOf(R.id.tvUserName)
                            rightMargin = dip(5)
                        }

                        editText {
                            id = R.id.edtEmailSignUp
                            textSize =  15f
                            hint = "Masukan Email Anda"
                            inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS or InputType.TYPE_CLASS_TEXT
                        }.lparams(matchParent, wrapContent){
                            rightOf(R.id.tvEmaiSignUp)
                            below(R.id.edtUserName)
                            baselineOf(R.id.tvEmaiSignUp)
                            rightMargin = dip(5)
                        }

                        editText {
                            id = R.id.edtPassSingUp
                            textSize =  15f
                            hint = "Masukan Password anda"
                            inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
                        }.lparams(matchParent, wrapContent){
                            rightOf(R.id.tvPass)
                            below(R.id.edtEmailSignUp)
                            baselineOf(R.id.tvPass)
                            rightMargin = dip(5)
                        }
                        editText {
                            id = R.id.edtRePass
                            textSize =  15f
                            hint = "Masukan kembali password anda"
                            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        }.lparams(matchParent, wrapContent){
                            rightOf(R.id.tvRePass)
                            below(R.id.edtPassSingUp)
                            baselineOf(R.id.tvRePass)
                            rightMargin = dip(5)

                        }

                button {
                    id = R.id.btnRegister
                    textSize = 16f
                    textColor = Color.WHITE
                    background =ContextCompat.getDrawable(ctx, R.drawable.tombol_bulat)
                    text = context.getString(R.string.register)
                }.lparams(matchParent, wrapContent){
                    below(R.id.tvRePass)
                    margin = dip(10)
                }
                    }

            }

        }
    }


