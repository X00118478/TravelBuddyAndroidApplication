package com.travelbuddyapp.www.travelbuddy

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.Arrays.asList




//import okhttp3.OkHttpClient


class FillUpActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fill_up)
//    TodoItem.getInstance()

    lateinit var mDatabase : DatabaseReference

    var mAuth = FirebaseAuth.getInstance()
    var user = FirebaseAuth.getInstance().currentUser

    //Edit Text Boxes Declaration
     var pricePerLitre = findViewById<EditText>(R.id.pricePerLitre)
    //focus the keyboard on the field then progress
    //pricePerLitre.setOnFocusChangeListener(this)
     var litresEditText = findViewById<EditText>(R.id.litres)
     var odometerEditText = findViewById<EditText>(R.id.odometerReading)

     //Button Declaration
     var saveEntry = Button(this)
     var cancelEntry = Button(this)
    //Count amount of times the save button is pushed
     var counter = 0
    val savesuccess = Intent(this, Timeline::class.java)
    val savefailure = Intent(this, FillUpActivity::class.java)


    //Cloud Storage
    //Storing on the Cloud
    //write to the FireBase database
    val database = FirebaseDatabase.getInstance()
    val myFireId = database.getReference("ID")
    val myFireVolumeOfLitres = database.getReference("VolumeOfLitres")
    val myFireCostPerLitreCostPerLitre = database.getReference("CostPerLitre")
    val myFireOdometerReading = database.getReference("OdometerReading")

//    val n = ArrayList<String>()
//
////your button
//    val b: Button
//
/////your edittext
//    val e: EditText
//
//
//    if (saveEntry.isPressed)
//      n.add(edit.getText().toString())

    //SAVE BUTTON
    //Save the new Entry of the data and return to the main activity
    saveEntry = findViewById(R.id.save)
    //When Save is hit get the data from the text fields in the fill_up_activity
    saveEntry.setOnClickListener {

      //Increments the counter each time save is hit.
      counter++
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

      val priceList = ArrayList(asList(0.0))
      val litresList = ArrayList(asList(0.0))
      if (saveEntry.isPressed){
        priceList.add(odometerReading)
        litresList.add(volumeOfLitres)}



      if(volumeOfLitres != null && costPerLitre != null && odometerReading != null)
        {
          try {

            //Increments the counter each time save is hit.
            counter++

            //Storing on the client side
            //object of FillUpData()
            val fillUpData = FillUpData()

            //Set the values
            fillUpData.id = counter
            fillUpData.volumeOfLitres = volumeOfLitres
            fillUpData.costPerLitre = costPerLitre
            fillUpData.odometerReading = odometerReading

            if(pricePerLitreValue.isEmpty())
            {
              pricePerLitre.error = "Please enter a Price Per Litre."
            }
            if(litresValue.isEmpty())
            {
              litresEditText.error = "Please enter total amount of Litre."
            }
            if(odometerValue.isEmpty())
            {
              odometerEditText.error = "Please enter Odometer Reading."
            }

            //Cloud Storage
            myFireId.push().setValue(counter)
            myFireVolumeOfLitres.push().setValue(volumeOfLitres)
            myFireCostPerLitreCostPerLitre.push().setValue(costPerLitre)
            myFireOdometerReading.push().setValue(odometerReading)
//            val uid = user!!.uid
//            mDatabase.child(uid).child("Names").setValue(volumeOfLitres)
//            mDatabase.child(uid).child("Names").setValue(costPerLitre)
//            mDatabase.child(uid).child("Names").setValue(odometerReading)
//            myRef.child("Names").push().setValue("VolumeOfLitres",volumeOfLitres)
//            myRef.child("Names").push().setValue("CostPerLitre",costPerLitre)
//            myRef.child("Names").push().setValue("OdometerReading",odometerReading)


            startActivity(savesuccess)
            finish()

          }
          catch (e:Exception)
          {


            Toast.makeText(this,"Save unsuccessful, Try again.",Toast.LENGTH_LONG).show()
            startActivity(savefailure)
            finish()
          }

        }
      else
      {
        try
        {
          Toast.makeText(this,"Please fill up the fields to save.",Toast.LENGTH_LONG).show()

          startActivity(savefailure)
          finish()
        }
        catch (e:Exception)
        {
          Toast.makeText(this,"Please fill up the fields to save.",Toast.LENGTH_LONG).show()
          startActivity(savefailure)
          finish()
        }
      }

    }

    // Create a DB Object to add the entries.
//    val dbHandler = DBHandler(this)


    //Display the inserts in a log.
//    Log.d("Insert: ", "Inserting ......")


      //Cancel the Add new Fill up entry request
      cancelEntry = findViewById(R.id.cancel)
      cancelEntry.setOnClickListener {
        // Handler code here.
        val cancelIntent = Intent(this, Timeline::class.java)
        startActivity(cancelIntent)
        finish()
      }

  }
}
