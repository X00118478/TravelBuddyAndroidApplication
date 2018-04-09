package com.travelbuddyapp.www.travelbuddy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserData : AppCompatActivity() {

  lateinit var mDatabase: DatabaseReference
  var mAuth = FirebaseAuth.getInstance()
  var user = FirebaseAuth.getInstance().currentUser
  val database = FirebaseDatabase.getInstance()
  val myFireCostPerLitreCostPerLitre = database.getReference("CostPerLitre")
  val costMap = HashMap<Double, Travel>()
  lateinit var fillUpCostList: ArrayList<Travel>
  lateinit var listView: ListView
//  val costlist : ArrayList<Double>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_user_data)

//    initCost()
    fillUpCostList = ArrayList()

    listView = findViewById(R.id.listView)
    //Edit Text Boxes Declaration
//    var pricePerLitre = findViewById<EditText>(R.id.pricePerLitre)
//    //Converting Value to toString then parse to Double.
//    var pricePerLitreValue = pricePerLitre.text.toString()
    //Parse the toString() to a Double so the value can be stored in the DB
//    var costPerLitre = java.lang.Double.parseDouble(pricePerLitreValue)

//    var currentExpense = findViewById<TextView>(R.id.current_Expense)
//    var currentExpenseValue = currentExpense.text.toString()
//    var currentExpenseDouble  = java.lang.Double.parseDouble(currentExpenseValue)

    myFireCostPerLitreCostPerLitre.addValueEventListener(object : ValueEventListener
    {
      override fun onDataChange(p0: DataSnapshot?) {
        if (p0!!.exists())
        {
          fillUpCostList.clear()
          p0.children
            .map { it.getValue(Travel::class.java) }
            .forEach { fillUpCostList.add(it!!) }
          val adapter = TravelAdapter(applicationContext, R.layout.activity_user_data,fillUpCostList)
          listView.adapter = adapter
        }
      }

      override fun onCancelled(p0: DatabaseError?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
      }

    })


//    val rootRef = myFireCostPerLitreCostPerLitre.child("CostPerLitre")
//    rootRef.addListenerForSingleValueEvent(object : ValueEventListener {
//      override fun onCancelled(error: DatabaseError?) {
//        println(error!!.message)
//      }
//
//      override fun onDataChange(snapshot: DataSnapshot?) {
//        val children = snapshot!!.children
//        children.forEach {
//          val adapter = TravelAdapter(applicationContext, R.layout.activity_user_data,fillUpCostList)
//          listView.adapter = adapter
//          println(it.toString())
//        }
//      }
//    })


  }
}
