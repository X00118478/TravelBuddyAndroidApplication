package com.travelbuddyapp.www.travelbuddy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by Alex Mac Uaid on 21/01/2018.
 */

public class FillUpData extends Activity {

 // EditText costPerLitre,volumeOfLitres,odemeterReading;

  Button save;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fill_up);

    /*
    final DBHandler db = new DBHandler(this);
    Log.d("Insert: ", "Inserting ..");
    */


  }



  //Attempt 1
  //declare the variables which will link to the button and SQL Lite
  private int id;
  private double costPerLitre;
  private double volumeOfLitres;
  private double odemeterReading;

  //Default Constructor
  public FillUpData()
  {

  }

  //Overloaded Constructor
  public FillUpData(int id, double costPerLitre,double volumeOfLitres,double odemeterReading)
  {
    this.id = id;
    this.costPerLitre = costPerLitre;
    this.volumeOfLitres = volumeOfLitres;
    this.odemeterReading = odemeterReading;
  }

  public void setId(int id)
  {
    this.id = id;
  }
  //Setters
  public void setCostPerLitre(double costPerLitre)
  {

    this.costPerLitre = costPerLitre;
  }
  public void setVolumeOfLitres(double volumeOfLitres)
  {


    this.volumeOfLitres = volumeOfLitres;
    }


  public void setOdometerReading(double odemeterReading)
  {
    this.odemeterReading = odemeterReading;
  }
  //Getters
  public int getId()
  {
    return this.id;
  }
  public double getCostPerLitre()
  {
    return this.costPerLitre;
  }
  public double getVolumeOfLitres()
  {
    return this.volumeOfLitres;
  }
  public double getOdometerReading()
  {

    return this.odemeterReading;
  }

}
