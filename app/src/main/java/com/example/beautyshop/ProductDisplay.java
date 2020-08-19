package com.example.beautyshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductDisplay extends AppCompatActivity {

    ListView lvs ;
    ArrayList<Product> products =new ArrayList<>();
    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);

        lvs = findViewById(R.id.lv);


        CustomAdapter_Product csv = new CustomAdapter_Product(this,retriveprodct());
                lvs.setAdapter(csv);




    }





    public ArrayList<Product> retriveprodct(){

        myRef = FirebaseDatabase.getInstance().getReference("Product");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               // products.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Product p= ds.getValue(Product.class);
                    //   Toast.makeText(getApplicationContext(),"Addingg Data", Toast.LENGTH_LONG).show();
                      products.add(p);
                    Toast.makeText(getApplicationContext()," Data " + String.valueOf(p.getProbrand()), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return products;

    }

    public void fetchDataproduct(DataSnapshot dataSnapshot)
    {
    //    Toast.makeText(getApplicationContext(),"Fetch data Called", Toast.LENGTH_LONG).show();





    }


}
