package com.example.arcore_face_filter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    val vm: LoginActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton =findViewById<Button>(R.id.loginButton)
        val signupButton =findViewById<Button>(R.id.signupButton)
        val email = findViewById<EditText>(R.id.loginTextEmailAddress)
        val pass = findViewById<EditText>(R.id.loginTextPassword)

        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        vm.uid.observe(this){
            if(it != null){
                println("User Successfully Logged in")
            }
        }

        loginButton.setOnClickListener {
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            vm.login(email.text.toString(), pass.text.toString())
        }


        signupButton.setOnClickListener {
            val toSignup = Intent(this, SignupActivity::class.java)
            startActivity(toSignup)
        }

        vm.msg.observe(this) {
            it?.let {
                if (it.isNotEmpty())
                    Snackbar.make(email, "Unable to login $it", Snackbar.LENGTH_LONG).show()
            }
        }

        vm.uid.observe(this) {
            // When the UID is not null, we transition to the main screen
            it?.let {
                val toMain = Intent(this, MainActivity::class.java)
                toMain.putExtra("email", email.text.toString())
                print(email.text.toString())
                startActivity(toMain)
            }
        }
    }
}


