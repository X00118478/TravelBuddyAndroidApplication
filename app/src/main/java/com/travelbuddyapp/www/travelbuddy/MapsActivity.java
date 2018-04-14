package com.travelbuddyapp.www.travelbuddy;
/***************************************************************************************

 *    Usage: Based On
 *    Title: Google Maps Nearby Places Tutorial | PART 1 ( Android Tutorials )
 *    Author: Tech Academy
 *    Date: 2017
 *    Code version: 1.0
 *    Availability: https://www.youtube.com/watch?v=_Oljjn1fIAc
 *
 ***************************************************************************************/

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
  String tag = "MapsActivity:";

  String courseLocation = android.Manifest.permission.ACCESS_COARSE_LOCATION;
  final int locationPermissionRequestCode = 1234;
  float defaultDeptOfField = 18f;

  //vars
  private Boolean deviceLocationPermissionsGranted = false;
  private GoogleMap googleMapsRef;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps);

    getLocationPermission();
  }

  private void getLocationPermission() {
    Log.d(tag, "getLocationPermission: Retrieve the location permissions");
    //Store the permissions in an Array of Strings
    String[] permissionsList = {android.Manifest.permission.ACCESS_FINE_LOCATION,
      android.Manifest.permission.ACCESS_COARSE_LOCATION};

    String fineLocation = android.Manifest.permission.ACCESS_FINE_LOCATION;
    if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
      fineLocation) == PackageManager.PERMISSION_GRANTED) {
      if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
        courseLocation) == PackageManager.PERMISSION_GRANTED) {
        deviceLocationPermissionsGranted = true;
        initiliseMap();
      } else {
        ActivityCompat.requestPermissions(this,
          permissionsList,
          locationPermissionRequestCode);
      }
    } else {
      ActivityCompat.requestPermissions(this,
        permissionsList,
        locationPermissionRequestCode);
    }
  }

  private void initiliseMap() {
    Log.d(tag, "initiliseMap: Creating Map");
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

    mapFragment.getMapAsync(MapsActivity.this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    googleMapsRef = googleMap;
    Toast.makeText(this, "Map Launched", Toast.LENGTH_SHORT).show();
    Log.d(tag, "onMapReady: Map Launched");

    if (deviceLocationPermissionsGranted) {
      getUserLocation();


      if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        return;
      }
      googleMapsRef.setMyLocationEnabled(true);
      googleMapsRef.getUiSettings().setMyLocationButtonEnabled(false);


    }
  }

  private void getUserLocation() {
    Log.d(tag, "getUserLocation: Retrieving User Current Location");

    FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

    try {
      if (deviceLocationPermissionsGranted) {

        final Task lastLocation = fusedLocationProviderClient.getLastLocation();
        lastLocation.addOnCompleteListener(new OnCompleteListener() {
          @Override
          public void onComplete(@NonNull Task task) {
            if (task.isSuccessful()) {
              //If the task is a success then get user location, move the map and zoom in.
              Log.d(tag, "getUserLocation: Location Located.");
              Location userLocation = (Location) task.getResult();

              //get the user latitude and longitude coordinates, stored as degrees then zoom the map in to 18F
              moveMap(new LatLng(userLocation.getLatitude(), userLocation.getLongitude()),
                defaultDeptOfField);

            } else {
              //If the if permission is not granted then display an error toast.
              Log.d(tag, "getUserLocation: current location unrecognised.");
              Toast.makeText(MapsActivity.this, "Current Location not available,Try Again Later.", Toast.LENGTH_SHORT).show();
            }
          }
        });
      }
    } catch (SecurityException e) {
      Log.e(tag, "getUserLocation: Security Exception Statement: " + e.getMessage());
    }
  }


  // move map to the latitude and longitude coordinates, stored as degrees.
  private void moveMap(LatLng latLng, float zoomtoDept) {
    Log.d(tag, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude);
    googleMapsRef.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomtoDept));
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissionsArray, @NonNull int[] resultsArray) {
    Log.d(tag, "onRequestPermissionsResult: called.");
    deviceLocationPermissionsGranted = false;

    switch (requestCode) {
      case locationPermissionRequestCode: {
        if (resultsArray.length > 0) {
          for (int i = 0; i < resultsArray.length; i++) {
            if (resultsArray[i] != PackageManager.PERMISSION_GRANTED) {
              deviceLocationPermissionsGranted = false;
              Log.d(tag, "onRequestPermissionsResult: Access failed");
              return;
            }
          }
          Log.d(tag, "onRequestPermissionsResult: Access granted");
          deviceLocationPermissionsGranted = true;
          //initialize our map
          initiliseMap();
        }
      }
    }
  }


}
