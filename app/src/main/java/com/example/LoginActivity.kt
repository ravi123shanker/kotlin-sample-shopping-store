package com.example

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLogin.setOnClickListener {
            val email=edtEmail.text.toString();
            val password=edtPassword.text.toString();
            var intent= Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
