package com.example.mymap.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mymap.Login.Login_info;
import com.example.mymap.Model.UserSingleton;
import com.example.mymap.Model.firebaseUser;
import com.example.mymap.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView pro_name;
    TextView pro_email;
    TextView pro_meal;
    TextView pro_aadhar;
    TextView pro_mobile;
    ImageButton editprofile;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ImageView prof_image;
    String uid;
    ProgressBar progressBar;
    firebaseUser firebas;
    UserSingleton userSingleton;
    Button logout;
    Button share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init_navi();
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.profile));
        }
         editprofile=findViewById(R.id.editButton);
         pro_name=findViewById(R.id.prof_full_name);
         pro_email=findViewById(R.id.prof_email);
         pro_meal=findViewById(R.id.pro_gender);
         pro_aadhar=findViewById(R.id.pro_aadhar);
         pro_mobile=findViewById(R.id.pro_mobile);
         prof_image=findViewById(R.id.pro_user_imageview);
         progressBar=findViewById(R.id.pro_progressbar);
         logout=findViewById(R.id.pro_logout);
         share=findViewById(R.id.pro_share);
          userSingleton=UserSingleton.getInstance("","","","");
         uid=userSingleton.uid;

         firebaseDatabase=FirebaseDatabase.getInstance();
         databaseReference=firebaseDatabase.getReference().child("main_users");
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firebas==null){
                    Toast.makeText(ProfileActivity.this, "wait", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(ProfileActivity.this, EditProfileActivity.class);
                intent.putExtra("mobile",firebas.getMobile());
                intent.putExtra("name",firebas.getName());
                intent.putExtra("gendre",firebas.getGender());
                intent.putExtra("aadhar",userSingleton.adharno);
                intent.putExtra("uid",firebas.getUser_uid());
                intent.putExtra("image",firebas.getImage());
                intent.putExtra("email",firebas.getEmail());
                startActivity(intent);
            }
        });

        getDataFromDb();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(ProfileActivity.this, Login_info.class);
                startActivity(intent);
                finishAffinity();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out my app at: https://drive.google.com/file/d/1N3Ma3QQYXgPO4omakTh9uW0SeQ4hZ1-f/view?usp=drivesdk");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }

    private void getDataFromDb() {
        databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                 firebas=snapshot.getValue(firebaseUser.class);
                setData();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void setData() {
        progressBar.setVisibility(View.GONE);
        Glide.with(this)
                .load(firebas.getImage())
                .centerCrop()
                .placeholder(R.drawable.harshad)
                .into(prof_image);
        pro_name.setText(firebas.getName());
        pro_email.setText(firebas.getEmail());
        pro_meal.setText(firebas.getGender());
        pro_mobile.setText(firebas.getMobile());
        pro_aadhar.setText(userSingleton.adharno);
        firebas.setAdharno(userSingleton.adharno);

    }


    protected  void init_navi(){
        bottomNavigationView = findViewById(R.id.bottom_nevigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.home:
                        intent = new Intent(ProfileActivity.this, Bottom_view.MapsActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.book:
                        intent = new Intent(ProfileActivity.this, MainActivity2.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.booking:
                        intent = new Intent(ProfileActivity.this, RidesActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.profile:

                        return true;

                }
                return false;
            }
        });
    }
}