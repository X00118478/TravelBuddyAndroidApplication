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
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.util.*




class Timeline : AppCompatActivity(), Runnable {

    lateinit var mDatabase : DatabaseReference
  var mAuth = FirebaseAuth.getInstance()
  var user = FirebaseAuth.getInstance().currentUser

  var rand = Random()
  var handler = Handler()

  override fun run() {
    fun tipRun ()
    {



      handler.post(this)

    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_dashboard)

    // Graphs on the Dashboard.
    val graphPrice = findViewById<View>(R.id.graphprice) as GraphView
    val seriesPrice = LineGraphSeries<DataPoint>(arrayOf<DataPoint>(DataPoint(0.0, 0.0), DataPoint(1.0, 5.0), DataPoint(2.0, 3.0), DataPoint(3.0, 2.0), DataPoint(4.0, 6.0)))
    graphPrice.addSeries(seriesPrice)
    graphPrice.title = "Cost (Monthly)"

    val graphFuel = findViewById<View>(R.id.graphfuel) as GraphView
    val seriesFuel = LineGraphSeries<DataPoint>(arrayOf<DataPoint>(DataPoint(0.0, 0.0), DataPoint(1.0, 5.0), DataPoint(2.0, 3.0), DataPoint(3.0, 2.0), DataPoint(4.0, 6.0)))
    graphFuel.addSeries(seriesFuel)
    graphFuel.title = "Fuel Usage (Monthly)"

    val graphPrediciton = findViewById<View>(R.id.graphPrediction) as GraphView
    val seriesPrediciton = LineGraphSeries<DataPoint>(arrayOf<DataPoint>(DataPoint(0.0, 0.0), DataPoint(1.0, 5.0), DataPoint(2.0, 3.0), DataPoint(3.0, 2.0), DataPoint(10.0, 60.0)))
    graphPrediciton.addSeries(seriesPrediciton)
      graphPrediciton.title = "Cost Prediciton (Yearly)"

// custom label formatter to show currency "EUR"
    graphPrice.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
      override fun formatLabel(value: Double, isValueX: Boolean): String {
        return if (isValueX) {
          // show normal x values
          super.formatLabel(value, isValueX)
        } else {
          // show currency for y values
          super.formatLabel(value, isValueX) + "€"
        }
      }
    }


    val tipList = ArrayList<String>()
    tipList.add("Warm Up Your Car for Shorter Lengths of Time. ")
    tipList.add("Purchase Petrol Early or Late in the Day. ")
    tipList.add("Slow Down and Drive Steady. ")
    tipList.add("Turn Off the Engine. ")
    tipList.add("Eliminate Wind Resistance. ")
    tipList.add("Avoid Petrol Stations Near the Motorway. ")
    tipList.add("Don’t Wait Until Your Tank Is Almost Empty to Fill Up. ")
    tipList.add("Monitor Your Tires. " )
    tipList.add("Tune the Engine. ")
    tipList.add("Change Filters. " )
    tipList.add("Use the Correct Motor Oil. ")
    tipList.add("Turn Off the A/C. ")
    tipList.add("Manage Your Speed. ")
    tipList.add("Choose the Best Route. ")
    tipList.add("Drive in off-peak times.")

    val tipSelected = rand.nextInt(tipList.size)
    val tipText = findViewById<TextView>(R.id.tip)

    tipText.text = tipList[tipSelected]




//    for (i in 0..tipList.size) {
//      val tipText = findViewById<TextView>(R.id.tip)
//      tipText.text = tipList[i]
//    }


// custom label formatter to show currency "EUR"
    graphPrediciton.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
      override fun formatLabel(value: Double, isValueX: Boolean): String {
        return if (isValueX) {
          // show normal x values
          super.formatLabel(value, isValueX)
        } else {
          // show currency for y values
          super.formatLabel(value, isValueX) + "€"
        }
      }
    }



//    TodoItem.getInstance()
    var fillupbutton = Button(this)
    fillupbutton = findViewById<Button>(R.id.newFillUp)
    fillupbutton .setOnClickListener {
      // Handler code here.
      val intent = Intent(this, FillUpActivity::class.java)
      startActivity(intent)
    }

    var refreshbutton = Button(this)
    refreshbutton = findViewById<Button>(R.id.refresh)
    refreshbutton .setOnClickListener {
      // Handler code here.
      startActivity(intent)
    }
  }
}
