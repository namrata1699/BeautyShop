package com.example.beautyshop.ui.gallery;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import static android.content.Context.MODE_PRIVATE;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;
   // SharedPreferences prf;
   // String username;

    public GalleryViewModel() {
     //   mText = new MutableLiveData<>();
       // mText.setValue("This is Profile fragment");

    }


    public LiveData<String> getText() {

        return mText;
    }
}