package com.example.beautyshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.nio.file.Files;

public class admin_insert_product_page extends AppCompatActivity {

        ProgressBar p1;
            EditText ename , eprice , ecategory ;
            Spinner sp;
            ImageView imgv;
            String name , price , category , imgurl , brand;
            Button chs_file,upload;
            String arryspin[]={"Colorrbar","Lakme","Maybelline","Huda Beauty" , "Revlon" , "Mac"};

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    private StorageTask muUploadTask;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_insert_product_page);

       ename = findViewById(R.id.edit_text_product_name);
       eprice = findViewById(R.id.edit_text_product_price);
       ecategory = findViewById(R.id.edit_text_product_category);
       sp = findViewById(R.id.sp_product_brand);
       chs_file = findViewById(R.id.button_choose_image);
       upload = findViewById(R.id.button_upload);
       imgv = findViewById(R.id.image_view1);
       p1 = findViewById(R.id.progress_bar);

        mStorageRef = FirebaseStorage.getInstance().getReference("Admin_Product");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Admin_Product");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arryspin);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                brand = arryspin[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


       chs_file.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openFileChooser();
           }
       });

       upload.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(muUploadTask != null && muUploadTask.isInProgress())
               {
                   Toast.makeText(getApplicationContext(),"Upload is in progress", Toast.LENGTH_LONG).show();
               }else
               {
                   uploadFile();
               }
           }
       });


    }



    private  void openFileChooser(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            mImageUri = data.getData();

            imgv.setImageURI(mImageUri);


        }
    }


    private String getFileExtension(Uri ur){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(ur));
    }



    private void uploadFile(){



        if(mImageUri != null)
        {
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "."
                    + getFileExtension(mImageUri));
//            StorageReference fileReference = null;

            muUploadTask =  fileReference.putFile(mImageUri)

                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {



                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    p1.setProgress(0);
                                }
                            },5000);



                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //   u = uri.toString();
                                    //   Toast.makeText(getApplicationContext(),"DOw called" + u,Toast.LENGTH_SHORT).show();
                                    //  u.equals(uri.toString());


                                    Admin_Product upload = new Admin_Product(ename.getText().toString(),eprice.getText().toString(),
                                            brand,ecategory.getText().toString(),uri.toString());
                                    Toast.makeText(getApplicationContext() ,"Upload Successful",Toast.LENGTH_LONG).show();
                                    String UploadId = mDatabaseRef.push().getKey();
                                    mDatabaseRef.child(UploadId).setValue(upload);
                                }
                            });



                            //  upload.getmImageUrl();
                            //   Toast.makeText(getApplicationContext(),"Out of it " ,Toast.LENGTH_LONG).show();



                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    })

                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            p1.setProgress((int)progress);
                        }
                    });
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No File Selected " , Toast.LENGTH_LONG).show();
        }
    }
}
