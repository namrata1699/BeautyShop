package com.example.beautyshop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdminImageAdapter extends RecyclerView.Adapter<AdminImageAdapter.ImageViewHolder>   {

    private Context mcontext;
    private List<Admin_Product> muploads;

    public AdminImageAdapter(Context mcontext, List<Admin_Product> muploads) {
        this.mcontext = mcontext;
        this.muploads = muploads;
    }

    public AdminImageAdapter() {
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.admin_layout_displayimgs,parent,false);

        return new AdminImageAdapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, final int position) {

        Admin_Product adminprod = muploads.get(position);
        holder.textviewName.setText(adminprod.getName());
        holder.textviewbrand.setText(adminprod.getBrand());
        holder.textviewcat.setText(adminprod.getCategory());
        holder.textviewprice.setText(adminprod.getPrice());

        Picasso.get().load(adminprod.getMimgURL()).fit().centerCrop().into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Admin_Product dd = muploads.get(position);
                String name , pri , cate , brn , imguri;
                name = dd.getName();
                pri = dd.getPrice();
                cate = dd.getCategory();
                brn = dd.getBrand();
                imguri = dd.getMimgURL();

                Intent intent = new Intent(mcontext.getApplicationContext(), DisplayFullProduct.class);
                intent.putExtra("Name",name);
                intent.putExtra("Price",pri);
                intent.putExtra("Category",cate);
                intent.putExtra("Brand",brn);
                intent.putExtra("Image",imguri);
                mcontext.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return muploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        public TextView textviewName,textviewprice,textviewcat,textviewbrand;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textviewName = itemView.findViewById(R.id.Name);
            textviewprice = itemView.findViewById(R.id.Price);
            textviewcat = itemView.findViewById(R.id.Category);
            textviewbrand = itemView.findViewById(R.id.Brand);
            imageView = itemView.findViewById(R.id.img);
        }
    }
}
