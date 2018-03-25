package com.travelbuddyapp.www.travelbuddy

/***************************************************************************************

 *    Usage: Based On
 *    Title: Firebase Authentication using Kotlin 2
 *    Author: Winision
 *    Date: 2018
 *    Code version: 1.0
 *    Availability: https://www.youtube.com/watch?v=ZhD_J3kcSjw
 *
 ***************************************************************************************/
/***************************************************************************************

 *    Usage: Based On
 *    Title: Set up Firebase Authentication for Android
 *    Author: Firebase
 *    Date: 2018
 *    Code version: 1.0
 *    Availability: https://firebase.google.com/docs/auth/android/start/
 *
 ***************************************************************************************/

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

    //loginButton & registerTest is non nullable
    val loginButton = findViewById<View>(R.id.loginButton) as Button
    val registerText = findViewById<View>(R.id.registerText) as TextView

    //when the button is selected then progress to the method call
    loginButton.setOnClickListener(View.OnClickListener
    {
      view -> login()
    })

    registerText.setOnClickListener(View.OnClickListener
    {
      view -> register()
    })

  }

  //this method will check the details exist in the Firebase DB & allow the user to log in or stop them completely Displaying a message in each case
  private fun login () {
    val emailTxt = findViewById<View>(R.id.emailText) as EditText
    var email = emailTxt.text.toString()
    val passwordTxt = findViewById<View>(R.id.passwordText) as EditText
    var password = passwordTxt.text.toString()

    if (!email.isEmpty() && !password.isEmpty()) {
      this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener ( this, OnCompleteListener<AuthResult> { task ->
        if (task.isSuccessful) {
          startActivity(Intent(this, Timeline::class.java))
          Toast.makeText(this, "Log in Successful", Toast.LENGTH_LONG).show()
        } else {
          Toast.makeText(this, "The email or password you have entered is invalid. ", Toast.LENGTH_SHORT).show()
        }
      })

    }else {
      Toast.makeText(this, "Text fields can not be blank!", Toast.LENGTH_SHORT).show()
    }
  }

  //This method opens the register activity  class
  private fun register () {
    val intent = Intent(this, register::class.java)
    startActivity(intent)
  }
}
