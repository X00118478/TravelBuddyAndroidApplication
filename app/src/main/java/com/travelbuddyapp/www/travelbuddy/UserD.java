package com.travelbuddyapp.www.travelbuddy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by AlexMcQuade on 10/04/2018.
 */

public class UserD extends Activity {
  private static final  String TAG = "UserData";

  //Firebase Requirments
  private FirebaseDatabase mFirebaseDatabase;
  private FirebaseAuth mAuthentication;
  private FirebaseAuth.AuthStateListener mAuthListner;
  private DatabaseReference myReference;
  private String userID;

  private String name;

  private ListView listView;
  private TextView textView;



  @Override
  protected void onCreate( Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_data);

//    listView = (ListView) findViewById(R.id.listView);
    textView = (TextView)findViewById(R.id.nTextView);

    mAuthentication = FirebaseAuth.getInstance();
    mFirebaseDatabase = FirebaseDatabase.getInstance();
    myReference = mFirebaseDatabase.getReference().child("User");
    FirebaseUser user = mAuthentication.getCurrentUser();
    userID = user.getUid();



    myReference.child(userID).child("Name").addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        User userdata = dataSnapshot.getValue(User.class);
//        userdata.
//        User userdata = new  User();
//        userdata.setName(dataSnapshot.child(userID).child("Name").getValue(User.class).getName());
//         name = userdata.getName();
        textView.setText("Name : " + userdata);

      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

        System.out.println("The read failed: " + databaseError.getCode());

      }
    });



    //ListView
//    myReference.addValueEventListener(new ValueEventListener() {
//      @Override
//      public void onDataChange(DataSnapshot dataSnapshot) {
//        displayData(dataSnapshot);
//      }
//
//      @Override
//      public void onCancelled(DatabaseError databaseError) {
//
//      }
//    });

  }
//  private void displayData(DataSnapshot dataSnapshot)
//  {
//    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
//    {
//      User userdata = new  User();
//      //Get & Set Email
//      userdata.setCostPerLitre(dataSnapshot1.child(userID).child("FillUp").getValue(User.class).getCostPerLitre());
//      //Get & Set Name
//      userdata.setOdometerReading(dataSnapshot1.child(userID).child("FillUp").getValue(User.class).getOdometerReading());
//      //Get & Set Password
//      userdata.setVolumeOfLitres(dataSnapshot1.child(userID).child("FillUp").getValue(User.class).getVolumeOfLitres());
//
//      //Log the Data
//      Log.d(TAG,"Data: Name: " + userdata.getCostPerLitre());
//      Log.d(TAG,"Data: Email: " + userdata.getOdometerReading());
//      Log.d(TAG,"Data: Password: " + userdata.getVolumeOfLitres());
//
//      //Array to store all the data gathered.
//      ArrayList<String> arrayList = new ArrayList<>();
////      arrayList.add(userdata.getCostPerLitre());
////      arrayList.add(userdata.getOdometerReading());
////      arrayList.add(userdata.getVolumeOfLitres());
//
//      ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
//      listView.setAdapter(adapter);
//    }
//  }

}
