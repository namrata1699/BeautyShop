package com.example.beautyshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    String user_em, user_pss;
    Button btn_ck;
    boolean b = false;
    EditText e1, e2;
    TextView sign_link;
    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference myRef = database.getReference();
    String username,contact,residentaladd;
    SharedPreferences prf;
    TextView addproduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_ck = findViewById(R.id.btn_login);
        e1 = findViewById(R.id.user_email);
        e2 = findViewById(R.id.pwd);
        addproduct = findViewById(R.id.AddProduct);
        sign_link = findViewById(R.id.signup_link);
        prf = getSharedPreferences("user_details",MODE_PRIVATE);

        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),admin_insert_product_page.class);
                startActivity(intent);
            }
        });



        sign_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),sign_act.class);
                startActivity(intent);
            }
        });

        btn_ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_em = e1.getText().toString();
                user_pss = e2.getText().toString();

                Boolean ck = retrive();
                if(ck == true)
                {
                    Toast.makeText(getApplicationContext(),"Login Successfull" , Toast.LENGTH_LONG).show();
                    e1.setText("");
                    e2.setText("");

                    SharedPreferences.Editor editor = prf.edit();
                    editor.putString("Username",username);
                    editor.putString("Contactno",contact);
                    editor.putString("Address",residentaladd);
                    editor.commit();
                    Intent intent=new Intent(Login.this,MainActivity.class);

                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Login UnSuccessfull" , Toast.LENGTH_LONG).show();

                }
            }
        });


    }

    public boolean retrive() {


        myRef = FirebaseDatabase.getInstance().getReference("Users");

        myRef.orderByChild("emaild").equalTo(user_em).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    User us = ds.getValue(User.class);
              //      Toast.makeText(getApplicationContext(), "Data " + us.getEmaild(), Toast.LENGTH_LONG).show();
                    String u = us.getEmaild();
                    String p = us.getPassword();


                    if(p.equals(user_pss))
                    {
                        b = true;
                        username = us.getNm();
                        residentaladd = us.getAddry();
                        contact = us.getContactno();
                    }
                    else
                    {
                        b = false;
                        username = null;
                        residentaladd = null;
                        contact = null;
                    }



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return b;
    }
}



