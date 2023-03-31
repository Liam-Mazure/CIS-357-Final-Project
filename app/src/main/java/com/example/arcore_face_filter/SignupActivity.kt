package com.example.arcore_face_filter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val crtAccountBtn =findViewById<Button>(R.id.crtAccountBtn)
        crtAccountBtn.setOnClickListener {
            val toMain = Intent(this, MainActivity::class.java)
            startActivity(toMain)
        }

        val cancelButton =findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            finish()
        }
    }
}