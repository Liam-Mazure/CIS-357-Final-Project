package com.example.arcore_face_filter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton =findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            val toMain = Intent(this, MainActivity::class.java)
            startActivity(toMain)
        }

        val signupButton =findViewById<Button>(R.id.signupButton)
        signupButton.setOnClickListener {
            val toSignup = Intent(this, SignupActivity::class.java)
            startActivity(toSignup)
        }
    }
}


