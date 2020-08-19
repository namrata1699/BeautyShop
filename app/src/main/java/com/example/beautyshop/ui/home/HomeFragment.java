package com.example.beautyshop.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beautyshop.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    RecyclerView rc;
    int images[]={R.drawable.colorbar,R.drawable.lakme,R.drawable.maybelline,R.drawable.huda,R.drawable.revlon,
    R.drawable.mac};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        CarouselView carouselView;
      final  int[] sampleImages = {R.drawable.img5, R.drawable.img4, R.drawable.img3, R.drawable.img2, R.drawable.img1};





        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        carouselView = root.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        });


        rc = root.findViewById(R.id.my_recycler_stall);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(linearLayoutManager);

        CustomAdapter cs = new CustomAdapter(getActivity(),images);
        rc.setAdapter(cs);

//        ImageListener imageListener = new ImageListener() {
//            @Override
//            public void setImageForPosition(int position, ImageView imageView) {
//                imageView.setImageResource(sampleImages[position]);
//            }
//        };
        return root;
    }
}