//package com.travelbuddyapp.www.travelbuddy;
//
//import android.app.Dialog;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
///**
// * Created by AlexMcQuade on 10/04/2018.
// */
//
//public class UserDataL extends AppCompatActivity{
//  ListView listView;
//  ArrayAdapter<Double> adapter;
//  DatabaseReference db;
//  FirebaseHelper helper;
//
//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_user_data);
//
//    listView= findViewById(R.id.listView);
//
//    //SETUP FIREBASE
//    db= FirebaseDatabase.getInstance().getReference();
//    helper=new FirebaseHelper(db);
//
//    //ADAPTER
//    adapter=new ArrayAdapter<Double>(this,android.R.layout.simple_list_item_1,helper.retrieve());
//    listView.setAdapter(adapter);
//
//    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//      @Override
//      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//       // Toast.makeText(UserDataL.this, helper.retrieve().get(position), Toast.LENGTH_SHORT).show();
//
//      }
//    });
//
//    FloatingActionButton fab = findViewById(R.id.fab);
//    fab.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View view) {
//        displayInputDialog();
//      }
//    });
//  }
//
//  private void displayInputDialog()
//  {
//    Dialog d=new Dialog(this);
//    d.setTitle("Save To Firebase");
//    d.setContentView(R.layout.input_dialog);
//
//
//        //GET DATA
//        //Double cost=getCostPerLitre;
//        Double costs[] =  helper.costList();
//        //SET DATA
//        Travel cost=new Travel();
//        cost.setCostPerLitre(cost);
//
//        //VALIDATE
//        if(name.length()>0 && name != null)
//        {
//          if(helper.save(cost))
//          {
//            nameEditTxt.setText("");
//            adapter=new ArrayAdapter<Double>(UserDataL.this,android.R.layout.simple_list_item_1,helper.retrieve());
//            listView.setAdapter(adapter);
//
//          }
//        }else
//        {
//          Toast.makeText(UserDataL.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
//        }
//
//      }
//    });
//
//    d.show();
//  }
//
//}
