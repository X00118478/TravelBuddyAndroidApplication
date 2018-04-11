//package com.travelbuddyapp.www.travelbuddy
//
//import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
//import android.util.Log
//import android.widget.ListView
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.ValueEventListener
//
//class UserData : AppCompatActivity() {
//
//  lateinit var mDatabase : DatabaseReference
//  val mAuth = FirebaseAuth.getInstance()
//  val user = mAuth.currentUser
//  //unique Identification
//  val uid = user!!.uid
//  private val TAG = "USERDATA"
//
//  val costMap = HashMap<Double, Travel>()
//  lateinit var fillUpCostList: ArrayList<Travel>
//  lateinit var listView: ListView
////  val costlist : ArrayList<Double>()
//
//  override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_user_data)
//    setContentView(R.layout.content)
//
//    fillUpCostList = ArrayList()
//    listView = findViewById(R.id.listView)
//
////    val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout)
////
////    val listView = ListView(this)
////
////    val values = arrayOf(
////      "Rick and Morty",
////      "Gaeme of Thrones",
////      "Silicon Valley",
////      "IT Crowd",
////      "Person of Interest")
////
////    val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values)
////
////    listView.adapter =adapter
////
////    constraintLayout.addView(listView)
//
//    mDatabase.child(uid).child("FillUp").child("Cost").addValueEventListener(object :ValueEventListener
//    {
//      override fun onDataChange(snapshot: DataSnapshot?) {
//        if (snapshot!!.exists())
//        {
//          val children = snapshot!!.children
//          val costList = java.util.ArrayList<Double>()
//
//         val fillUpData = snapshot!!.getValue(FillUpData::class.java)
//
//          if (fillUpData == null)
//          {
//            Log.e(TAG,"User Data is Null!")
//            return
//          }
//
//        }
//      }
//
//      override fun onCancelled(p0: DatabaseError?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//      }
//
//    })
//
//  }
//
//
//}
