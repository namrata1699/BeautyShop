package com.example.beautyshop;

public class UploadImageEx {

    private String mName;
    private String mImageUrl;

    public UploadImageEx() {

    }

    public UploadImageEx(String mName, String mImageUrl) {
        if(mName.trim().equals(""))
        {
            mName="No Name";

        }
        this.mName = mName;
        this.mImageUrl = mImageUrl;
    }


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
