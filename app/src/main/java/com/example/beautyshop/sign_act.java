package com.example.beautyshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sign_act extends AppCompatActivity {

    Button btn_save;
    EditText e1,e2,e3,e4,e5;
    String name,contact,em,pass,add;


    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference myRef = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_act);

        e1= findViewById(R.id.user_name);
        e2 = findViewById(R.id.user_con);
        e3 = findViewById(R.id.user_emaild);
        e4 = findViewById(R.id.user_pass);
        e5 = findViewById(R.id.user_add);
       btn_save = findViewById(R.id.btnsave);

       btn_save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                name = e1.getText().toString();
                contact = e2.getText().toString();
                em = e3.getText().toString();
                pass = e4.getText().toString();
                add = e5.getText().toString();
               createuser(name,contact,em,pass,add);
               Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_LONG).show();
               e1.setText("");
               e2.setText("");
               e3.setText("");
               e4.setText("");
               e5.setText("");
            Intent intent = new Intent(getApplicationContext(),Login.class);
            startActivity(intent);

           }
       });
    }



    public void createuser(String nm , String con , String email , String pass , String ad)
    {
        int flag=0;
        User user = new User(nm , con , email , pass , ad);
        myRef.child("Users").push().setValue(user);


    }
}
