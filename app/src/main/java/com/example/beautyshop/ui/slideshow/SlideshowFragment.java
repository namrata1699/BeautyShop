package com.example.beautyshop.ui.slideshow;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.beautyshop.CustomAdapter_Product;
import com.example.beautyshop.CustomerAdapter_Order;
import com.example.beautyshop.MainActivity;
import com.example.beautyshop.Order;
import com.example.beautyshop.Product;
import com.example.beautyshop.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    ArrayList<Order> ord =new ArrayList<>();
    ListView lst ;
    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference myRef = database.getReference();
    String username;
    SharedPreferences prf;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {





        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        prf = this.getActivity().getSharedPreferences("user_details",MODE_PRIVATE);
        username =  prf.getString("Username",null);
        lst = root.findViewById(R.id.listvieworder);

        myRef = FirebaseDatabase.getInstance().getReference("Order");
        myRef.orderByChild("customerName").equalTo(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Order p = ds.getValue(Order.class);
                   // Toast.makeText(getActivity(),"data  : " + p.getProdname() , Toast.LENGTH_LONG).show();
                    ord.add(p);
                }

                CustomerAdapter_Order csv = new CustomerAdapter_Order(getActivity(),ord);
                lst.setAdapter(csv);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








        return root;


    }




}