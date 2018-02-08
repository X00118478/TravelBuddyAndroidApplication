package com.travelbuddyapp.www.travelbuddy

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
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
