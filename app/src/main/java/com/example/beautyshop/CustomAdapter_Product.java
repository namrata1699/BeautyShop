package com.example.beautyshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter_Product extends BaseAdapter {

    Context c;
    ArrayList<Product> products;

    public CustomAdapter_Product(Context c, ArrayList<Product> products) {
        this.c = c;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            view = LayoutInflater.from(c).inflate(R.layout.modelproduct,viewGroup,false);
        }

        TextView t1 = view.findViewById(R.id.pname);
        TextView t2 = view.findViewById(R.id.price);
        TextView t3 = view.findViewById(R.id.pcat);
        TextView t4 = view.findViewById(R.id.pb);


        final  Product  p = (Product) CustomAdapter_Product.this.getItem(i);


        t1.setText( p.getProname());
        t2.setText( p.getProprice());
        t3.setText( p.getProcategory());
        t4.setText(  p.getProbrand());


        return view;
    }
}
