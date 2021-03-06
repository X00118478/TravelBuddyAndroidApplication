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


class FillUpActivity : AppCompatActivity() {

  lateinit var mDatabase: DatabaseReference
  val mAuth = FirebaseAuth.getInstance()
  val user = mAuth.currentUser
  //unique Identification
  val uid = user!!.uid


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fill_up)
//    TodoItem.getInstance()


    //Treelike structure
    mDatabase = FirebaseDatabase.getInstance().getReference("User")
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
      if (saveEntry.isPressed) {
        priceList.add(odometerReading)
        litresList.add(volumeOfLitres)
      }



      if (volumeOfLitres != null && costPerLitre != null && odometerReading != null) {
        try {

          //Increments the counter each time save is hit.
          counter++

          //Storing on the client side
          //object of FillUpData()
          val fillUpData = FillUpData()

          //Set the values
//          fillUpData.userID = user!!.displayName
          fillUpData.volumeOfLitres = volumeOfLitres
          fillUpData.costPerLitre = costPerLitre
          fillUpData.odometerReading = odometerReading

          if (pricePerLitreValue.isEmpty()) {
            pricePerLitre.error = "Please enter a Price Per Litre."
          }
          if (litresValue.isEmpty()) {
            litresEditText.error = "Please enter total amount of Litre."
          }
          if (odometerValue.isEmpty()) {
            odometerEditText.error = "Please enter Odometer Reading."
          }

          if (costPerLitre is Double) {
            val cost = costPerLitre.toInt()
            mDatabase.child(uid).child("FillUp").child("Cost").push().setValue(cost)
          } else {
            mDatabase.child(uid).child("FillUp").child("Cost").push().setValue(costPerLitre)
          }
          if (volumeOfLitres is Double) {
            val litre = volumeOfLitres.toInt()
            mDatabase.child(uid).child("FillUp").child("VolumeOfLitres").push().setValue(litre)
          } else {
            mDatabase.child(uid).child("FillUp").child("VolumeOfLitres").push().setValue(volumeOfLitres)
          }
          if (odometerReading is Double) {
            val odometer = odometerReading.toInt()
            mDatabase.child(uid).child("FillUp").child("OdometerReading").push().setValue(odometer)
          } else {
            mDatabase.child(uid).child("FillUp").child("OdometerReading").push().setValue(odometerReading)
          }


          startActivity(savesuccess)
          finish()

        } catch (e: Exception) {


          Toast.makeText(this, "Save unsuccessful, Try again.", Toast.LENGTH_LONG).show()
          startActivity(savefailure)
          finish()
        }

      } else {
        try {
          Toast.makeText(this, "Please fill up the fields to save.", Toast.LENGTH_LONG).show()

          startActivity(savefailure)
          finish()
        } catch (e: Exception) {
          Toast.makeText(this, "Please fill up the fields to save.", Toast.LENGTH_LONG).show()
          startActivity(savefailure)
          finish()
        }
      }

    }

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
