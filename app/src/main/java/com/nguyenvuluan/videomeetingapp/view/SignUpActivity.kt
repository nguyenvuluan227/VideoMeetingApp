package com.nguyenvuluan.videomeetingapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nguyenvuluan.videomeetingapp.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        clickView()
    }

    private fun clickView() {
        imvBack.setOnClickListener { onBackPressed() }
        tvSignIn.setOnClickListener { onBackPressed() }
    }
}