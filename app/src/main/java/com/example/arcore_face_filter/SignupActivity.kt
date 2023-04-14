package com.example.arcore_face_filter

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels

class SignupActivity : AppCompatActivity() {

    val vm: SignupActivityViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val email = findViewById<EditText>(R.id.signupTextEmailAddress)
        val pass = findViewById<EditText>(R.id.signupTextPassword)
        val pass2 = findViewById<EditText>(R.id.signupTextPassword2)
        val crtAccountBtn =findViewById<Button>(R.id.crtAccountBtn)
        val cancelButton =findViewById<Button>(R.id.cancelButton)

        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        crtAccountBtn.setOnClickListener {
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            vm.newAccount(email.text.toString(), pass2.text.toString())
            val toMain = Intent(this, MainActivity::class.java)
            startActivity(toMain)
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}