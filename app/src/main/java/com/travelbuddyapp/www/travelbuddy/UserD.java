package com.travelbuddyapp.www.travelbuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by AlexMcQuade on 10/04/2018.
 */

public class UserD extends AppCompatActivity {
  private static final String TAG = "UserData";


  //Firebase Requirments
  private FirebaseDatabase mFirebaseDatabase;
  private FirebaseAuth mAuthentication;
  private FirebaseAuth.AuthStateListener mAuthListner;
  private DatabaseReference myReference;
  private String userID;

  private String name;

  private ListView listView;
  private TextView textViewName;
  private TextView textViewEmail;
  private TextView texViewCost;
  private TextView textViewFuel;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_data);

    Toast.makeText(this, "User Profile Loading", Toast.LENGTH_SHORT).show();

//    listView = (ListView) findViewById(R.id.listView);
    textViewName = (TextView) findViewById(R.id.nTextView);
    textViewEmail = (TextView) findViewById(R.id.etextView);
    texViewCost = (TextView) findViewById(R.id.textViewTotalCost);
    textViewFuel = (TextView) findViewById(R.id.textViewF);

    mAuthentication = FirebaseAuth.getInstance();
    mFirebaseDatabase = FirebaseDatabase.getInstance();
    myReference = mFirebaseDatabase.getReference();
    FirebaseUser user = mAuthentication.getCurrentUser();
    userID = user.getUid();

    DatabaseReference child = myReference.child("User").child(user.getUid());

    child.addListenerForSingleValueEvent(new ValueEventListener() {

      @Override
      public void onDataChange(DataSnapshot datasnapshot) {

        if (datasnapshot.exists()) {
          ArrayList<Long> costList = new ArrayList<>();
          ArrayList<Long> fuelList = new ArrayList<>();
          String name;
          String email;
          String password;
          long sumCost = 0;
          long sumFuel = 0;
          email = (String) datasnapshot.child("Email").getValue();
          name = (String) datasnapshot.child("Name").getValue();

          for (DataSnapshot childSnapshot : datasnapshot.child("FillUp").child("Cost").getChildren()) {
            costList.clear();
            costList.add((Long) childSnapshot.getValue());




            for (Long na : costList) {
              sumCost = sumCost + na;
            }
          }

          for (DataSnapshot childSnapshot : datasnapshot.child("FillUp").child("VolumeOfLitres").getChildren()) {
            fuelList.clear();
            fuelList.add((Long) childSnapshot.getValue());

            for (Long na : fuelList) {
              sumFuel = sumFuel + na;
            }
          }

          //Output the Values retreived from Firebase
          textViewName.setText("User Name: " + name);
          textViewEmail.setText("User Email: " + email);
          texViewCost.setText("Cost Total: €" + sumCost);
          textViewFuel.setText("Litres Purchased: " + sumFuel);

        } else {
          Log.d(TAG, "User Name Missing.");
          textViewName.setText("User Name: ERROR");
          Toast.makeText(getBaseContext(),"Error Loading Database",Toast.LENGTH_SHORT).show();
        }

      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

        System.out.println("The read failed: " + databaseError.getCode());

      }
    });
  }
}
