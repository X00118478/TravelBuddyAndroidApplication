package com.travelbuddyapp.www.travelbuddy

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
//import okhttp3.OkHttpClient


class FillUpActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fill_up)
//    TodoItem.getInstance()

    //Edit Text Boxes Declaration
     var pricePerLitre = findViewById<EditText>(R.id.pricePerLitre)
    //focus the keyboard on the field then progress
    //pricePerLitre.setOnFocusChangeListener(this)
     var litresEditText = findViewById<EditText>(R.id.litres)
     var odometerEditText = findViewById<EditText>(R.id.odometerReading)

     //Button Declaration
     var saveEntry = Button(this)
     var cancelEntry = Button(this)




    //SAVE BUTTON
    //Save the new Entry of the data and return to the main activity
    saveEntry = findViewById(R.id.save)
    //When Save is hit get the data from the text fields in the fill_up_activity
    saveEntry.setOnClickListener {

      //Converting Value to toString then parse to Double.
      var pricePerLitreValue = pricePerLitre.text.toString()
      //Parse the toString() to a Double so the value can be stored in the DB
      var costPerLitre = java.lang.Double.parseDouble(pricePerLitreValue)
      //fun String.toDouble(): Double = java.lang.Double.parseDouble(this)

      //Convert value to toString() then parse to Type Double
      var litresValue = litresEditText.text.toString()
      var volumeOfLitres = java.lang.Double.parseDouble(litresValue)

      //Convert value to toString() then parse to Type Double
      var odometerValue = odometerEditText.text.toString()
      var odometerReading = java.lang.Double.parseDouble(odometerValue)

      //object of FillUpData()
      val fillUpData = FillUpData()

      //Set the values
      fillUpData.volumeOfLitres = volumeOfLitres
      fillUpData.costPerLitre = costPerLitre
      fillUpData.odometerReading = odometerReading

      val saveIntent = Intent(this, Timeline::class.java)
      startActivity(saveIntent)

    }

    // Create a DB Object to add the entries.
    val dbHandler = DBHandler(this)


    //Display the inserts in a log.
    Log.d("Insert: ", "Inserting ......")


      //Cancel the Add new Fill up entry request
      cancelEntry = findViewById(R.id.cancel)
      cancelEntry.setOnClickListener {
        // Handler code here.
        val cancelIntent = Intent(this, Timeline::class.java)
        startActivity(cancelIntent)
      }

  }
}
