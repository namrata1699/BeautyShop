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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.net.URI;

public class ex_img extends AppCompatActivity {

    public String u="";
    Button choose_img,upload;
    EditText filename;
    ImageView img_view;
    ProgressBar p1;
    TextView showupload;
    private static final int PICK_IMAGE_REQUEST = 1;
     private Uri mImageUri;

        private StorageReference mStorageRef;
        private DatabaseReference mDatabaseRef;
        private StorageTask muUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_img);
       choose_img = findViewById(R.id.button_choose_image);
       upload = findViewById(R.id.button_upload);
       filename = findViewById(R.id.edit_text_file_name);
       img_view = findViewById(R.id.image_view);
       p1 = findViewById(R.id.progress_bar);
       showupload = findViewById(R.id.text_view_show_uploads);


       mStorageRef = FirebaseStorage.getInstance().getReference("ImageClass");
       mDatabaseRef = FirebaseDatabase.getInstance().getReference("ImageClass");

       choose_img.setOnClickListener(new View.OnClickListener() {
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


       showupload.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               OpenImagesActivity();

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

        img_view.setImageURI(mImageUri);


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

                                    UploadImageEx upload = new UploadImageEx(filename.getText().toString().trim(),uri.toString());
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


    private void OpenImagesActivity(){
        Intent intent = new Intent(this,Display_imgex.class);
        startActivity(intent);
    }


}
