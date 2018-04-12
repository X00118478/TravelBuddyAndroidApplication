package com.travelbuddyapp.www.travelbuddy;

import android.support.annotation.Keep;

/**
 * Created by AlexMcQuade on 10/04/2018.
 */

public class User {

  private String name;
  private String email;
  private String password;
  private String costPerLitre;
  private String volumeOfLitres;
  private String odometerReading;

  public String getName() {
    return name;
  }

  //  @PropertyName("Name")
  @Keep
  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  @Keep
  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  @Keep
  public void setPassword(String password) {
    this.password = password;
  }

  public User() {
  }

  public User(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }


  public String getCostPerLitre() {
    return costPerLitre;
  }

  @Keep
  public void setCostPerLitre(String costPerLitre) {
    this.costPerLitre = costPerLitre;
  }

  public String getVolumeOfLitres() {
    return volumeOfLitres;
  }

  @Keep
  public void setVolumeOfLitres(String volumeOfLitres) {
    this.volumeOfLitres = volumeOfLitres;
  }

  public String getOdometerReading() {
    return odometerReading;
  }

  @Keep
  public void setOdometerReading(String odometerReading) {
    this.odometerReading = odometerReading;
  }

}
