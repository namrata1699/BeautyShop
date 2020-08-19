package com.example.beautyshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class insert_userform extends AppCompatActivity {

    ListView lv,lv2;
    EditText e1,e2,e3,e4,e5,e6;
    String name,contact,email,passwd,addr,id;
    Button b1,b2;
    ArrayList<User> users=new ArrayList<>();


   public FirebaseDatabase database = FirebaseDatabase.getInstance();
  public   DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_userform);

        lv = findViewById(R.id.lv);

        e1 = findViewById(R.id.name);
        e2 = findViewById(R.id.contact);
        e3 = findViewById(R.id.emaild);
        e4 = findViewById(R.id.pass);
        e5 = findViewById(R.id.addry);

        b1 = findViewById(R.id.save);
        b2 = findViewById(R.id.procu);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {

             name = e1.getText().toString();
             contact = e2.getText().toString();
             email = e3.getText().toString();
             passwd = e4.getText().toString();
             addr = e5.getText().toString();
            id = e6.getText().toString();
            createuser(id,name,contact,email,passwd,addr);
                Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_LONG).show();

            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = e1.getText().toString();
                contact = e2.getText().toString();
                email = e3.getText().toString();
                passwd = e4.getText().toString();
                createproduct(name,contact,email,passwd);
                Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_LONG).show();
            }
        });




        CustomAdapterListview cs = new CustomAdapterListview(this,retrive());
        lv.setAdapter(cs);






    }

    public void createuser(String id,String nm , String con , String email , String pass , String ad)
    {
        int flag=0;
         User user = new User(nm , con , email , pass , ad);
        myRef.child("Users").push().setValue(user);

    }


    public void createproduct(String nm , String price , String cate , String brand )
    {
        int flag=0;
        Product p = new Product(nm , price , cate , brand);
        myRef.child("Product").push().setValue(p);

    }


    private void fetchData(DataSnapshot dataSnapshot)
    {
//        users.clear();
//
//        for (DataSnapshot ds : dataSnapshot.getChildren())
//        {
//            User us=ds.getValue(User.class);
//            Toast.makeText(getApplicationContext(),"Data " + us.getNm(),Toast.LENGTH_LONG).show();
//            users.add(us);
//        }
    }





    public ArrayList<User> retrive(){

        myRef = FirebaseDatabase.getInstance().getReference("Users");

        myRef.orderByChild("nm").equalTo("xjje").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    User us=ds.getValue(User.class);
                    Toast.makeText(getApplicationContext(),"Data " + us.getNm(),Toast.LENGTH_LONG).show();
                    users.add(us);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                users.clear();
//
//                for (DataSnapshot ds : dataSnapshot.getChildren())
//                {
//                    User us=ds.getValue(User.class);
//                    Toast.makeText(getApplicationContext(),"Data " + us.getNm(),Toast.LENGTH_LONG).show();
//                    users.add(us);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

//        myRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                fetchData(dataSnapshot);
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                fetchData(dataSnapshot);
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        return users;

    }




}
