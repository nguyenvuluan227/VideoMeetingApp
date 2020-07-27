package com.nguyenvuluan.videomeetingapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nguyenvuluan.videomeetingapp.R
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        clickView()
    }

    private fun clickView() {
        tvSignUp.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    SignUpActivity::class.java
                )
            )
        }
    }
}