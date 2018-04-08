package com.travelbuddyapp.www.travelbuddy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserData : AppCompatActivity() {

  lateinit var mDatabase : DatabaseReference
  var mAuth = FirebaseAuth.getInstance()
  var user = FirebaseAuth.getInstance().currentUser
  val database = FirebaseDatabase.getInstance()
  val myFireCostPerLitreCostPerLitre = database.getReference("costPerLitre")
  lateinit var fillUpCostList: MutableList<Travel>
  lateinit var listView: ListView
//  val costlist : ArrayList<Double>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_user_data)

//    initCost()
    fillUpCostList = mutableListOf()

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

          for (h in p0.children)
          {
            fillUpCostList.clear()
            val fillUpDataTotal = h.getValue(Travel::class.java)
            fillUpCostList.add(fillUpDataTotal!!)
          }
          val adapter = TravelAdapter(applicationContext, R.layout.activity_user_data,fillUpCostList)
          listView.adapter = adapter
        }
      }

      override fun onCancelled(p0: DatabaseError?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
      }

    })

//    var overallCost = costPerLitre + currentExpenseDouble


//    currentExpense.text = overallCost.toString()

  }

//  private fun initCost() {
//    val listener = object : ValueEventListener {
//      override fun onDataChange(dataSnapshot: DataSnapshot) {
//        costlist.clear()
//        dataSnapshot.(costlist) { it.getValue<FillUpData>(FillUpData::class.java) }
//      }
//
//      override fun onCancelled(databaseError: DatabaseError) {
//        println("loadPost:onCancelled ${databaseError.toException()}")
//      }
//    }
//    firebaseData.child("salads").addListenerForSingleValueEvent(listener)
//  }



}
