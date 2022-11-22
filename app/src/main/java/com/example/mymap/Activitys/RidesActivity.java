package com.example.mymap.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.mymap.Adapters.BookingAdapter;
import com.example.mymap.Model.Booking_model;
import com.example.mymap.Model.UserSingleton;
import com.example.mymap.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RidesActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;
    ArrayList<Booking_model> arrayList;
    BookingAdapter bookingAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ShimmerFrameLayout shimmerFrameLayout;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rides);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.gray));
        }
        arrayList=new ArrayList<>();
        shimmerFrameLayout=findViewById(R.id.booking_shimmer);
        shimmerFrameLayout.startShimmer();
        recyclerView=findViewById(R.id.recycler_booked);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookingAdapter=new BookingAdapter(this,arrayList);
        recyclerView.setAdapter(bookingAdapter);
        textView=findViewById(R.id.empty_booking);
        UserSingleton userSingleton=UserSingleton.getInstance("","","","");
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("ticket_book");
        databaseReference.child(userSingleton.uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    arrayList.add(dataSnapshot.getValue(Booking_model.class));
                }
                if(arrayList.size()>0){
                    textView.setVisibility(View.GONE);
                }
                else {
                    textView.setVisibility(View.VISIBLE);
                }
                bookingAdapter.notifyDataSetChanged();
                shimmerFrameLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        init_navi();


    }

    protected  void init_navi(){
        bottomNavigationView = findViewById(R.id.bottom_nevigation);
        bottomNavigationView.setSelectedItemId(R.id.booking);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.home:
                        intent = new Intent(RidesActivity.this, Bottom_view.MapsActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.book:
                        intent = new Intent(RidesActivity.this, MainActivity2.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.profile:
                        intent = new Intent(RidesActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.booking:
                        return true;
                }
                return false;
            }
        });
    }
}