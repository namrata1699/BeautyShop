package com.example.beautyshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class DisplayFullProduct extends AppCompatActivity {

    Button add;
    TextView t1,t2,t3,t4;
    ImageView img;
    String username , contact , address;
    SharedPreferences prf;
    String ModeofPayment;
     RadioGroup rg;
    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_full_product);
        add = findViewById(R.id.btn_addtocart);
        img = findViewById(R.id.img_final);
        t1 = findViewById(R.id.name_final);
        t2 = findViewById(R.id.price_final);
        t3 = findViewById(R.id.brand_final);
        t4 = findViewById(R.id.cat_final);
        rg = findViewById(R.id.modeofpayment);
        prf = getSharedPreferences("user_details",MODE_PRIVATE);

      //  createuser(name,contact,em,pass,add);

        t1.setText(getIntent().getExtras().getString("Name"));
        t2.setText(getIntent().getExtras().getString("Price"));
        t3.setText(getIntent().getExtras().getString("Brand"));
        t4.setText(getIntent().getExtras().getString("Category"));
        Picasso.get().load(getIntent().getExtras().getString("Image")).fit().centerCrop().into(img);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radioButton2)
                {
                 ModeofPayment = "Cash On Delievery";
                }else if(i == R.id.radioButton)
                {
                    ModeofPayment = "Online Payment";
                }
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = prf.edit();
                Toast.makeText(getApplicationContext(),"Item" + prf.getString("Username" ,null) ,Toast.LENGTH_LONG).show();
                String custname , contact , address,prod_name,prod_brand,prod_price;
                custname = prf.getString("Username",null);
                contact = prf.getString("Contact",null);
                address = prf.getString("Address",null);
                prod_name = t1.getText().toString();
                prod_price = t2.getText().toString();
                prod_brand = t3.getText().toString();

                makeorder(custname,contact,address,prod_name,prod_price,prod_brand,ModeofPayment);
                Toast.makeText(getApplicationContext(),"Order Place Succesfull ...ThankYou For Shopping",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DisplayFullProduct.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }



    public void makeorder(String cusname , String cuscon , String cusadd , String prodname , String prodprice,String probrand , String mop)
    {

//        User user = new User(nm , con , email , pass , ad);
      //  myRef.child("Users").push().setValue(user);

        Order od = new Order(cusname,cuscon,cusadd,prodname,prodprice,probrand,mop,"Pending");
        myRef.child("Order").push().setValue(od);


    }
}
