package com.example.beautyshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Display_prowithimg extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ProgressBar pb ;
    private AdminImageAdapter mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<Admin_Product> adminUploads;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_prowithimg);

        pb = findViewById(R.id.progressBarfnal);
        mRecyclerView = findViewById(R.id.recycle);
        adminUploads = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Admin_Product");
        String brand = getIntent().getExtras().getString("Brand");
        mDatabaseRef.orderByChild("brand").equalTo(brand).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot PostSnapshot : dataSnapshot.getChildren()){
                    Admin_Product adp = PostSnapshot.getValue(Admin_Product.class);
                    adminUploads.add(adp);
                    Toast.makeText(getApplicationContext(),"Adding Data",Toast.LENGTH_LONG).show();
                }

                GridLayoutManager mgrid = new GridLayoutManager(Display_prowithimg.this,2);
                mRecyclerView.setLayoutManager(mgrid);
             //   mRecyclerView.setLayoutManager(new LinearLayoutManager(Display_prowithimg.this));
                mAdapter = new AdminImageAdapter(Display_prowithimg.this,adminUploads);

                mRecyclerView.setAdapter(mAdapter);

                pb.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pb.setVisibility(View.VISIBLE);
            }
        });



    }
}
