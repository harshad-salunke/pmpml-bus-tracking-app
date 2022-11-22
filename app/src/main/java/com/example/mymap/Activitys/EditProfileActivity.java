package com.example.mymap.Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mymap.Model.firebaseUser;
import com.example.mymap.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class EditProfileActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button update;
ProgressBar progressBar;
    EditText name;
    EditText email;
    EditText adhar_no;
    String dname;
    String demail;
    String dadhar;
    String gender;
    String mobile;
    String image;
    String uid;
ImageView imageView;
ImageView setImage;
firebaseUser users;
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
    FirebaseStorage storage;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.gray));
        }
        Intent intent=getIntent();
       mobile= intent.getStringExtra("mobile");
       dname= intent.getStringExtra("name");
        gender= intent.getStringExtra("gendre");
       dadhar= intent.getStringExtra("aadhar");
       uid= intent.getStringExtra("uid");
      image=  intent.getStringExtra("image");
      demail=  intent.getStringExtra("email");
      users=new firebaseUser(dname,mobile,dadhar,uid);

        name=findViewById(R.id.edit_name);
        email=findViewById(R.id.edit_email);
        adhar_no=findViewById(R.id.edit_adharno);
        radioGroup=findViewById(R.id.gender_but);
        update=findViewById(R.id.edit_update);
        imageView=findViewById(R.id.edit_imageview2);
        progressBar=findViewById(R.id.edit_progress);
        setImage=findViewById(R.id.set_image);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("main_users");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        name.setText(dname);
        email.setText(demail);
        adhar_no.setText(dadhar);
        Glide.with(this)
                .load(image)
                .centerCrop()
                .placeholder(R.drawable.harshad)
                .into(imageView);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radioButton2){
                    gender="male";
                }else{
                    gender="female";

                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update.setEnabled(false);
                progressBar.setVisibility(View.VISIBLE);
                dname=name.getText().toString();
                demail=email.getText().toString();
                dadhar=adhar_no.getText().toString();
                users.setUser_uid(uid);
                users.setAdharno(dadhar);
                users.setEmail(demail);
                users.setGender(gender);
                users.setName(dname);
                users.setImage(image);
                users.setMobile(mobile);
                setDatatoDb(users);
            }
        });

        setImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,1001);
                Toast.makeText(EditProfileActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1001){
            String url=data.getData().toString();
            Glide.with(this)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.harshad)
                    .into(imageView);
            Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
            uploadImage(url);
        }
    }

    private void setDatatoDb(firebaseUser users) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("CureentUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name",users.getName());
        editor.putString("mobile",users.getMobile());
        editor.putString("aadhar",users.getAdharno());
        databaseReference.child(uid).setValue(users).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                editor.commit();
                Toast.makeText(EditProfileActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                update.setEnabled(true);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(EditProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // UploadImage method
    private void uploadImage(String image2)
    {
        Uri filePath=Uri.parse(image2);
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                         image=uri.toString();
                                        }
                                    });

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(EditProfileActivity.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();


                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(EditProfileActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");

                                }
                            });
        }
    }
}