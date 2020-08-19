package com.example.beautyshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomerAdapter_Order  extends BaseAdapter {

    Context c;
    ArrayList<Order> orderobj;

    public CustomerAdapter_Order(Context c, ArrayList<Order> orderobj) {
        this.c = c;
        this.orderobj = orderobj;
    }

    public CustomerAdapter_Order() {
    }

    @Override
    public int getCount() {
        return orderobj.size();
    }

    @Override
    public Object getItem(int i) {
        return orderobj.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            view = LayoutInflater.from(c).inflate(R.layout.layout_orderdetails,viewGroup,false);
        }

        TextView t1 = view.findViewById(R.id.name);
        TextView t2 = view.findViewById(R.id.price);
        TextView t3 = view.findViewById(R.id.brand);
        TextView t4 = view.findViewById(R.id.modeofpay);
        TextView t5 = view.findViewById(R.id.odrstatus);

        final  Order  p = (Order) CustomerAdapter_Order.this.getItem(i);


        t1.setText( p.getProdname());
   //     Toast.makeText(c.getApplicationContext(),"In Adapter Dtaa" + p.getProdname() , Toast.LENGTH_LONG).show();
        t2.setText( p.getProdPrice());
        t3.setText( p.getProdBrand());
        t4.setText( p.getModeofpayment());
        t5.setText(p.getStaus());


        return view;
    }
}
