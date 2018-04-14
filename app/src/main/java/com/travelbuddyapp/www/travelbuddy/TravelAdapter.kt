package com.travelbuddyapp.www.travelbuddy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

/**
 * Created by AlexMcQuade on 05/04/2018.
 */
class TravelAdapter(val mCtx: Context, private val layoutResId: Int, val travelList: List<Travel>)
  : ArrayAdapter<Travel>(mCtx, layoutResId, travelList) {
  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

    val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
    val view: View = layoutInflater.inflate(layoutResId, null)

//    val current_Expense = view.findViewById<TextView>(R.id.current_Expense)

    val fillup = travelList[position]

    var pricePerLitreValue = fillup.costPerLitre


    return view
  }


}
