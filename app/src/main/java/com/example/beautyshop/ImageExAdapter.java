package com.example.beautyshop;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageExAdapter extends RecyclerView.Adapter<ImageExAdapter.ImageViewHolder> {
    private Context mcontext;
    private List<UploadImageEx> muploads;

    public ImageExAdapter(Context mcontext, List<UploadImageEx> muploads) {
        this.mcontext = mcontext;
        this.muploads = muploads;
    }

    public ImageExAdapter() {
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(mcontext).inflate(R.layout.display_img_layoutfile,parent,false);

        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, final int position) {
        UploadImageEx uploadcurrent = muploads.get(position);
        holder.textviewName.setText(uploadcurrent.getmName());
      //  holder.imageView.setImageResource(Integer.parseInt(uploadcurrent.getmImageUrl()));
       Picasso.get().load(uploadcurrent.getmImageUrl()).fit().centerCrop().into(holder.imageView);
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               UploadImageEx up = muploads.get(position);
               Toast.makeText(mcontext.getApplicationContext(),"Clicked Item Image" + up.getmName(),Toast.LENGTH_LONG).show();
           }
       });



    }

    @Override
    public int getItemCount() {
        return muploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        public TextView textviewName;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textviewName = itemView.findViewById(R.id.text_view_name);
            imageView = itemView.findViewById(R.id.image_view_upload);
        }
    }
}
