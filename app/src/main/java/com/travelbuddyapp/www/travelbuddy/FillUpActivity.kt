package com.travelbuddyapp.www.travelbuddy

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FillUpActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fill_up)

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
    saveEntry = findViewById<Button>(R.id.save)
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

      if (odometerReading.equals(0))
      {
        Toast.makeText(this,"You must enter a decimal into the Odometer Field!", Toast.LENGTH_SHORT).show()


      }


      //Set the values
      fillUpData.setVolumeOfLitres(volumeOfLitres)
      fillUpData.setCostPerLitre(costPerLitre)
      fillUpData.setOdometerReading(odometerReading)

      //fillUpData.costPerLitre

      val saveIntent = Intent(this, MainActivity::class.java)
      startActivity(saveIntent)

    }


//     var priceValue = 0.0
//     var litreValue = 0.0
//     var odometerValue = 0.0
     //Initiate an instance of FillUpData()



    // Create a DB Object to add the entries.
    val dbHandler = DBHandler(this)

    //Create Database Object
    //DBHandler database = new DBHandler();
    //val dataBase = DBHandler(0,pricePerLitre,litresEditText,odometerEditText)




    //Display the inserts in a log.
    Log.d("Insert: ", "Inserting ......")





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
    //  val odometerEditText = findViewById<EditText>(R.id.odometerReading)
      //val intent = Intent(this, FillUpActivity::class.java)
    }

  */
  }
}
