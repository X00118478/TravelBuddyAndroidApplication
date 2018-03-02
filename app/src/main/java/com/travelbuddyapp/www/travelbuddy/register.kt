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
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class register : AppCompatActivity() {

  val mAuth = FirebaseAuth.getInstance()
  lateinit var mDatabase : DatabaseReference

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_register)

    val registerBtn = findViewById<View>(R.id.regBtn) as Button


    //Treelike structure
    mDatabase = FirebaseDatabase.getInstance().getReference("Names")


    registerBtn.setOnClickListener(View.OnClickListener {
      view -> registerUser()
    })

  }

  private fun registerUser () {
    val emailTxt = findViewById<View>(R.id.emailTxt) as EditText
    val passwordTxt = findViewById<View>(R.id.passwordTxt) as EditText
    val nameTxt = findViewById<View>(R.id.nameTxt) as EditText

    var email = emailTxt.text.toString()
    var password = passwordTxt.text.toString()
    var name = nameTxt.text.toString()

    //If the Email & Password & Name are not empty then progress
    if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty()) {
      mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
       //if the stuff is stored correctly then
        if (task.isSuccessful) {
          //get the value of the current user
          val user = mAuth.currentUser
          //unique Identification
          val uid = user!!.uid
          mDatabase.child(uid).child("Name").setValue(name)
          //Progress to the next activity with the user logged in.
          startActivity(Intent(this, Timeline::class.java))
          Toast.makeText(this, "Successful Registration!", Toast.LENGTH_LONG).show()
        }
        // if the storing of data was not successful then
        else {
          Toast.makeText(this, "Error registering, try again later.", Toast.LENGTH_LONG).show()
        }
      })
    }
    //If the Text fields where left empty
    else {
      Toast.makeText(this,"Text fields can not be blank!", Toast.LENGTH_LONG).show()
    }
  }

}
