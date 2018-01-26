package com.travelbuddyapp.www.travelbuddy

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText

class FillUpActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fill_up)

     var pricePerLitre = findViewById<EditText>(R.id.pricePerLitre)
    var  pricePerLitreValue = pricePerLitre.text.toString()
    if (!pricePerLitreValue.isEmpty()) {
      try {
        fun String.toDouble(): Double = java.lang.Double.parseDouble(this)
      } catch (notDouble1: Exception ) {
        notDouble1.printStackTrace()
      }
    }
     var litresEditText = findViewById<EditText>(R.id.litres)
     var odemeterEditText = findViewById<EditText>(R.id.odemeterReading)
     var saveEntry = Button(this)
     var cancelEntry = Button(this)
     var priceValue = 0.0
     var litreValue = 0.0
     var odemeterValue = 0.0

     //Initiate an instance of FillUpData()
     val fillUpData = FillUpData()

    //Create Database Object
    //DBHandler database = new DBHandler();
    //val dataBase = DBHandler(0,pricePerLitre,litresEditText,odemeterEditText)




    //Display the inserts in a log.
    Log.d("Insert: ", "Inserting ......")




    //Save the new Entry of the data and return to the main activity
    saveEntry = findViewById<Button>(R.id.save)
    //When Save is hit get the data from the text fields in the fill_up_activity
    saveEntry.setOnClickListener {

      var litresValue = litresEditText.text.toString()
      var odemeterValue = odemeterEditText.text.toString()

      //fillUpData.costPerLitre

      val saveIntent = Intent(this, FillUpActivity::class.java)
      startActivity(saveIntent)

    }
      //Cancel the Add new Fill up entry request
      cancelEntry = findViewById<Button>(R.id.cancel)
      cancelEntry.setOnClickListener {
        // Handler code here.
        val cancelIntent = Intent(this, MainActivity::class.java)
        startActivity(cancelIntent)
      }

    /*
    //Retrieve data from the textFields
    fun saveDataEntry(view: View)
    {
     // EditText pricePerLitreEditText = (EditText) findViewById(R.id.pricePerLitre)
        //val pricePerLitreEditText = findViewById<EditText>(R.id.pricePerLitre)
    //  Double pricePerLitreEditTextValue = pricePerLitreEditText.getText().toString();
     // val litresEditText = findViewById<EditText>(R.id.litres)
    //  val odemeterEditText = findViewById<EditText>(R.id.odemeterReading)
      //val intent = Intent(this, FillUpActivity::class.java)
    }

  */
  }
}
