package com.travelbuddyapp.www.travelbuddy;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by AlexMcQuade on 10/04/2018.
 */

public class FirebaseHelper {

    DatabaseReference db;
    Boolean saved=null;
    ArrayList<Double> costList=new ArrayList<>();

    public FirebaseHelper(DatabaseReference db) {
      this.db = db;
    }

    //WRITE
    public Boolean save(Travel travel)
    {
      if(travel==null)
      {
        saved=false;
      }else
      {
        try
        {
          db.child("CostPerLitre").push().setValue(travel);
          saved=true;

        }catch (DatabaseException e)
        {
          e.printStackTrace();
          saved=false;
        }
      }

      return saved;
    }

    //READ
    public ArrayList<Double> retrieve()
    {
      db.addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
          fetchData(dataSnapshot);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
          fetchData(dataSnapshot);

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
      });

      return costList;
    }

    private void fetchData(DataSnapshot dataSnapshot)
    {
      costList.clear();

      for (DataSnapshot ds : dataSnapshot.getChildren())
      {
        Double cost=ds.getValue(Travel.class).getCostPerLitre();
        costList.add(cost);
      }
    }
  }
