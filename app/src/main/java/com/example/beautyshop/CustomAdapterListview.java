package com.example.beautyshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.beautyshop.ui.home.CustomAdapter;

import java.util.ArrayList;

public class CustomAdapterListview extends BaseAdapter {

    Context c;
    ArrayList<User> users;

    public CustomAdapterListview(Context c, ArrayList<User> users) {
        this.c = c;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            view = LayoutInflater.from(c).inflate(R.layout.model,viewGroup,false);
        }

        TextView t1 = view.findViewById(R.id.name);
        TextView t2 = view.findViewById(R.id.con);
        TextView t3 = view.findViewById(R.id.em);
        TextView t4 = view.findViewById(R.id.pss);
        TextView t5 = view.findViewById(R.id.add);

        final  User  u = (User) CustomAdapterListview.this.getItem(i);

        t1.setText(u.getNm());
        t2.setText(u.getContactno());
        t3.setText(u.getEmaild());
        t4.setText(u.getPassword());
        t5.setText(u.getAddry());



        return view;
    }
}
