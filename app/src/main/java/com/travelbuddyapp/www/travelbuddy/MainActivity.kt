package com.travelbuddyapp.www.travelbuddy

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

  var mAuth = FirebaseAuth.getInstance()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val loginButton = findViewById<View>(R.id.loginButton) as Button
    val registerText = findViewById<View>(R.id.registerText) as TextView

    loginButton.setOnClickListener(View.OnClickListener
    {
      view -> login()
    })

    registerText.setOnClickListener(View.OnClickListener
    {
      view -> register()
    })

  }

  private fun login () {
    val emailTxt = findViewById<View>(R.id.emailText) as EditText
    var email = emailTxt.text.toString()
    val passwordTxt = findViewById<View>(R.id.passwordText) as EditText
    var password = passwordTxt.text.toString()

    if (!email.isEmpty() && !password.isEmpty()) {
      this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener ( this, OnCompleteListener<AuthResult> { task ->
        if (task.isSuccessful) {
          startActivity(Intent(this, Timeline::class.java))
          Toast.makeText(this, "Successfully Logged in :)", Toast.LENGTH_LONG).show()
        } else {
          Toast.makeText(this, "Error Logging in :(", Toast.LENGTH_SHORT).show()
        }
      })

    }else {
      Toast.makeText(this, "Please fill up the Credentials :|", Toast.LENGTH_SHORT).show()
    }
  }

  private fun register () {
    val intent = Intent(this, register::class.java)
    startActivity(intent)
  }
}
