package com.travelbuddyapp.www.travelbuddy

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

/**
 * Created by AlexMcQuade on 17/02/2018.
 */


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

    if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty()) {
      mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
        if (task.isSuccessful) {
          //get the value of the current user
          val user = mAuth.currentUser
          //unique Identification
          val uid = user!!.uid
          mDatabase.child(uid).child("Name").setValue(name)
          startActivity(Intent(this, Timeline::class.java))
          Toast.makeText(this, "Successfully registered :)", Toast.LENGTH_LONG).show()
        }else {
          Toast.makeText(this, "Error registering, try again later :(", Toast.LENGTH_LONG).show()
        }
      })
    }else {
      Toast.makeText(this,"Please fill up the Credentials :|", Toast.LENGTH_LONG).show()
    }
  }

}
