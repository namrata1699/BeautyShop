package com.example.beautyshop.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.beautyshop.Display_prowithimg;
import com.example.beautyshop.R;
import com.example.beautyshop.sign_act;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    int[] img;
    String brand;


    public CustomAdapter(Context context, int[] img) {
        this.context = context;
        this.img = img;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.cardview_layoutt, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {


        holder.img.setImageResource(img[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(this,"cjlnc",)
                Toast.makeText(context.getApplicationContext(),"Clicked Item ",Toast.LENGTH_LONG).show();
                if(position == 0)
                {
                    brand = "Colorbar";
                }else if(position == 1)
                {
                    brand = "Lakme";
                }
                else if(position == 2)
                {
                    brand = "Maybelline";
                }
                else if(position == 3)
                {
                    brand = "Huda Beauty";
                }
                else if(position == 4)
                {
                    brand = "Revlon";
                }
                else if(position == 5)
                {
                    brand = "Mac";
                }
                Intent intent = new Intent(context.getApplicationContext(), Display_prowithimg.class);
                intent.putExtra("Brand",brand);
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return img.length;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.brands);

        }
    }
}
