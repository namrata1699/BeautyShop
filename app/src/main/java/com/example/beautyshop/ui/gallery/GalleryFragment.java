package com.example.beautyshop.ui.gallery;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.beautyshop.R;
import com.example.beautyshop.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);



//        final TextView textView = root.findViewById(R.id.text_gallery);
//        galleryViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        final TextView tname , tcontact , taddress , temail;
        tname = root.findViewById(R.id.name_pro);
        tcontact = root.findViewById(R.id.contact_pro);
        taddress = root.findViewById(R.id.address_pro);
        temail = root.findViewById(R.id.email_pro);

         FirebaseDatabase database = FirebaseDatabase.getInstance();
         DatabaseReference myRef = database.getReference();
        SharedPreferences prf;


       prf = this.getActivity().getSharedPreferences("user_details",MODE_PRIVATE);
       String username = prf.getString("Username",null);

        myRef = FirebaseDatabase.getInstance().getReference("Users");

        myRef.orderByChild("nm").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    User us = ds.getValue(User.class);

                    String name = us.getNm();
                    String emailid = us.getEmaild();
                    String contact = us.getContactno();
                    String address = us.getAddry();
                    tname.setText(name);
                    taddress.setText(address);
                    tcontact.setText(contact);
                    temail.setText(emailid);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return root;
    }
}