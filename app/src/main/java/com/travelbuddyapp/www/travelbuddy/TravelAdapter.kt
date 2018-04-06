package com.travelbuddyapp.www.travelbuddy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

/**
 * Created by AlexMcQuade on 05/04/2018.
 */
class TravelAdapter(val mCtx: Context,val  layoutResId: Int,val travelList: List<FillUpData>)
  :ArrayAdapter<FillUpData>(mCtx, layoutResId, travelList)
{
  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

    val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)
    val view:View = layoutInflater.inflate(layoutResId, null)

    val current_Expense = view.findViewById<TextView>(R.id.current_Expense)

    val fillup = travelList[position]

    var pricePerLitreValue = fillup.costPerLitre.toString()
    current_Expense.text = pricePerLitreValue

    return view
  }


}
