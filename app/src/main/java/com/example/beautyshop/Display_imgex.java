package com.example.beautyshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Display_imgex extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ImageExAdapter mAdapter;
    private ProgressBar pb ;
    private DatabaseReference mDatabaseRef;
    private List<UploadImageEx> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_imgex);

        pb = findViewById(R.id.progress_circle);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("ImageClass");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        for(DataSnapshot PostSnapshot : dataSnapshot.getChildren()){
            UploadImageEx u = PostSnapshot.getValue(UploadImageEx.class);
          //  Toast.makeText(getApplicationContext(),"Image URL" + u.getmImageUrl(),Toast.LENGTH_LONG).show();
            mUploads.add(u);
         //   Toast.makeText(getApplicationContext(),u.getmImageUrl(),Toast.LENGTH_LONG).show();
        }

        mAdapter = new ImageExAdapter(Display_imgex.this,mUploads);

        mRecyclerView.setAdapter(mAdapter);

        pb.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error" + databaseError.getMessage(),Toast.LENGTH_LONG).show();
                pb.setVisibility(View.VISIBLE);
            }
        });


    }
}
