package com.travelbuddyapp.www.travelbuddy

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class FillUpActivity : AppCompatActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fill_up)


    // DBHandler db = new DBHandler(this);

    //Inserting Fill Up Entry Rows
    Log.d("Insert: ", "Inserting ......");

    //Save the new Entry of the data and return to the main activity
    var saveEntry = Button(this)
    saveEntry = findViewById<Button>(R.id.save)

    saveEntry.setOnClickListener {
      // Handler code here.
      val intent = Intent(this, FillUpActivity::class.java)
      startActivity(intent)
      //Cancel the Add new Fill up entry request
      var cancelEntry = Button(this)
      cancelEntry = findViewById<Button>(R.id.cancel)
      cancelEntry.setOnClickListener {
        // Handler code here.
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
      }
    }

    fun saveDataEntry (view: View)
    {
      val pricePerLitreEditText = findViewById<EditText>(R.id.pricePerLitre)
      val litresEditText = findViewById<EditText>(R.id.litres)
      val odemeterEditText = findViewById<EditText>(R.id.odemeterReading)
      val intent = Intent(this, FillUpActivity::class.java);
    }

  }
}
