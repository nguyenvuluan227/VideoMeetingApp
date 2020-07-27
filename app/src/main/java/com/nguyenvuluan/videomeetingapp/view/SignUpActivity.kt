package com.nguyenvuluan.videomeetingapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.nguyenvuluan.videomeetingapp.R
import com.nguyenvuluan.videomeetingapp.utils.Constants
import com.nguyenvuluan.videomeetingapp.utils.PreferenceManager
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initView()
    }

    private fun initView() {

        preferenceManager = PreferenceManager(applicationContext)

        imvBack.setOnClickListener { onBackPressed() }
        tvSignIn.setOnClickListener { onBackPressed() }
        btnSignUp.setOnClickListener {
            if (edtFirstName.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter first name", Toast.LENGTH_SHORT).show()
            } else if (edtLastName.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter last name", Toast.LENGTH_SHORT).show()
            } else if (edtEmail.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(edtEmail.text.toString()).matches()) {
                Toast.makeText(this, "Enter valid email", Toast.LENGTH_SHORT).show()
            } else if (edtPassword.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
            } else if (edtConfirmPassword.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Confirm password", Toast.LENGTH_SHORT).show()
            } else if (!edtPassword.text.toString().equals(edtConfirmPassword.text.toString())) {
                Toast.makeText(
                    this,
                    "Password & confirm password must be the same",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                signUp()
            }
        }
    }

    private fun signUp() {
        btnSignUp.visibility = View.INVISIBLE
        pbSignUp.visibility = View.VISIBLE

        val database = FirebaseFirestore.getInstance()
        val user = HashMap<String, Any>()
        user[Constants.KEY_FIRST_NAME] = edtFirstName.text.toString()
        user[Constants.KEY_LAST_NAME] = edtLastName.text.toString()
        user[Constants.KEY_EMAIL] = edtEmail.text.toString()
        user[Constants.KEY_PASSWORD] = edtPassword.text.toString()

        database.collection(Constants.KEY_COLLECTION_USERS)
            .add(user)
            .addOnSuccessListener {
                preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true)
                preferenceManager.putString(Constants.KEY_FIRST_NAME, edtFirstName.text.toString())
                preferenceManager.putString(Constants.KEY_LAST_NAME, edtLastName.text.toString())
                preferenceManager.putString(Constants.KEY_EMAIL, edtEmail.text.toString())
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
            .addOnFailureListener {
                pbSignUp.visibility = View.INVISIBLE
                btnSignUp.visibility = View.VISIBLE
                Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}