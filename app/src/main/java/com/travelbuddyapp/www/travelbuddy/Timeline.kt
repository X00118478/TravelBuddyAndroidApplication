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
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference


class Timeline : AppCompatActivity() {

  lateinit var mDatabase : DatabaseReference
  var mAuth = FirebaseAuth.getInstance()
  var user = FirebaseAuth.getInstance().currentUser

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_dashboard)

//    TodoItem.getInstance()
    var fillupbutton = Button(this)
    fillupbutton = findViewById<Button>(R.id.newFillUp)
    fillupbutton .setOnClickListener {
      // Handler code here.
      val intent = Intent(this, FillUpActivity::class.java)
      startActivity(intent)
    }
  }


}
