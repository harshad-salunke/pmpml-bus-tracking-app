package com.example.mymap.Activitys;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymap.Adapters.DestinationsAtapter;
import com.example.mymap.Model.SubStation_model;
import com.example.mymap.Model.UserSingleton;
import com.example.mymap.R;
import com.example.mymap.databinding.ActivityMapsBinding;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Bottom_view extends BottomSheetDialogFragment {
String from;
String to;
ImageView alara_btn;
    public Bottom_view() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_bottom_view, container, false);
        alara_btn=rootView.findViewById(R.id.alaram_btn);
        RecyclerView recyclerView=rootView.findViewById(R.id.recyclerview);
        ArrayList<SubStation_model> arrayList=new ArrayList<>();
        arrayList=new ArrayList<>();
        arrayList.add(new SubStation_model("Swargate",from,to,"5"));
        arrayList.add(new SubStation_model("Laxmi Narayan Theatre",from,to,"30"));
        arrayList.add(new SubStation_model("Bhapkar Petrol Pump Brts",from,to,"60"));
        arrayList.add(new SubStation_model("Aranyeshwar Corner Brts",from,to,"70"));
        arrayList.add(new SubStation_model("Natu Baug Brts",from,to,"40"));
        arrayList.add(new SubStation_model("Padmavati Brts",from,to,"10"));
        arrayList.add(new SubStation_model(" Padmavati Brts",from,to,"20"));
        arrayList.add(new SubStation_model("Ahilyadevi Holkar Chowk (K.K Market) Brts",from,to,"50"));
        arrayList.add(new SubStation_model("Balajinagar Brts",from,to,"45"));
        arrayList.add(new SubStation_model(" Bharti Vidyapeeth Brts",from,to,"55"));
        arrayList.add(new SubStation_model(" Katraj Dairy-Sarpodyan Brts",from,to,"45"));
        arrayList.add(new SubStation_model(" More Baug Brts",from,to,"25"));
        arrayList.add(new SubStation_model(" Katraj",from,to,"15"));
        arrayList.add(new SubStation_model("Kondwa",from,to,"15"));
        arrayList.add(new SubStation_model("kondwa road",from,to,"40"));
        arrayList.add(new SubStation_model("Saswad",from,to,"35"));
        arrayList.add(new SubStation_model("Swargate",from,to,"45"));
        arrayList.add(new SubStation_model("Laxmi Narayan Theatre",from,to,"65"));
        arrayList.add(new SubStation_model("Bhapkar Petrol Pump Brts",from,to,"65"));
        arrayList.add(new SubStation_model("Aranyeshwar Corner Brts",from,to,"60"));
        arrayList.add(new SubStation_model("Natu Baug Brts",from,to,"100"));
        arrayList.add(new SubStation_model("Padmavati Brts",from,to,"90"));
        arrayList.add(new SubStation_model(" Padmavati Brts",from,to,"60"));
        arrayList.add(new SubStation_model("Ahilyadevi Holkar Chowk (K.K Market) Brts",from,to,"75"));
        arrayList.add(new SubStation_model("Balajinagar Brts",from,to,"85"));
        arrayList.add(new SubStation_model(" Bharti Vidyapeeth Brts",from,to,"95"));
        arrayList.add(new SubStation_model(" Katraj Dairy-Sarpodyan Brts",from,to,"70"));
        arrayList.add(new SubStation_model(" More Baug Brts",from,to,"5"));
        arrayList.add(new SubStation_model(" Katraj",from,to,"25"));
        arrayList.add(new SubStation_model("Kondwa",from,to,"15"));
        arrayList.add(new SubStation_model("kondwa road",from,to,"65"));
        arrayList.add(new SubStation_model("Saswad",from,to,"5"));

        Context context=getContext();
        DestinationsAtapter destinationsAtapter=new DestinationsAtapter(arrayList,getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(destinationsAtapter);
        destinationsAtapter.notifyDataSetChanged();
        TextView textView=rootView.findViewById(R.id.sub_to);
        TextView textView1=rootView.findViewById(R.id.sub_from);
        textView.setText(from);
        textView1.setText(to);

        alara_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return rootView;
    }
public void getValue(String from,String to){
        this.from=from;
        this.to=to;
}

    public static class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
        BottomNavigationView bottomNavigationView;
        private GoogleMap mMap;
        private ActivityMapsBinding binding;
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        Map<String, Marker> mNamedMarkers = new HashMap<String, Marker>();
        Map<String ,String> PerticulerMarker=new HashMap<String,String>();
        Geocoder geocoder;
        int ACCESSS_LOCATION_REQUEST_CODE = 1000;
        FusedLocationProviderClient fusedLocationProviderClient;
        LocationRequest locationRequest;
        Marker userMarker;
        Circle usercirclelocation;
        boolean firstTime=true;

        ArrayList<String> arr;
        ArrayAdapter<String> arrayAdapter;
        View Mapview;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            binding = ActivityMapsBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference().child("users");

            ChildEventListener markerUpdateListener = new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                    String key = dataSnapshot.getKey();
                    Users2 users2=dataSnapshot.getValue(Users2.class);
                    SetBusLocation(users2,key);


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                    String key = dataSnapshot.getKey();
                    Users2 users2 = dataSnapshot.getValue(Users2.class);
                    SetBusLocation(users2,key);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    String key = dataSnapshot.getKey();
                    Log.d("harhad", "Location for '" + key + "' was removed.");

                    Marker marker = mNamedMarkers.get(key);
                    if (marker != null)
                        marker.remove();
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                    // Unused
                    Log.d("harshad", "Priority for '" + dataSnapshot.getKey() + " was changed.");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w("harshad", "markerUpdateListener:onCancelled", databaseError.toException());
                }
            };
            if (Build.VERSION.SDK_INT >= 21) {
                Window window = this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(this.getResources().getColor(R.color.gray));
            }
            GetCurrentUser();

            databaseReference.addChildEventListener(markerUpdateListener);

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

            init_navi();

            Mapview=mapFragment.getView();
            geocoder = new Geocoder(this);
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

            locationRequest = LocationRequest.create();
            locationRequest.setInterval(500);
            locationRequest.setFastestInterval(500);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            arr=new ArrayList<>();
            arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arr);
            binding.carlist.setAdapter(arrayAdapter);

            binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    search_Bus_Location(query);
                    binding.carlist.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if(newText.equals("")){
                        binding.carlist.setVisibility(View.GONE);
                    }
                    else {
                    binding.carlist.setVisibility(View.VISIBLE);
                    }
                    arrayAdapter.getFilter().filter(newText);
                    return false;
                }
            });

            binding.carlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Object clickedItem=parent.getAdapter().getItem(position);
                    System.out.println(clickedItem.toString());
                    binding.searchView.setQuery(clickedItem.toString(),true);
                }
            });
        }

        private void GetCurrentUser() {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("CureentUser", MODE_PRIVATE);
            String names=pref.getString("name","test");
            String mobile=pref.getString("mobile","");
            String aadhar=pref.getString("aadhar","");
            String user_uid=pref.getString("user_uid","");
            UserSingleton userSingleton=UserSingleton.getInstance(names,mobile,aadhar,user_uid);

        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            mMap.setOnMarkerClickListener(this::onMarkerClick);
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode,permissions,grantResults);
            if (requestCode == ACCESSS_LOCATION_REQUEST_CODE) {
                if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startedLocationUpdate();
                } else {
                    //Permission not granted
                }
            }
        }



        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Log.d("locations are",locationResult.getLastLocation()+"");
                Location location = locationResult.getLastLocation();
                if (firstTime){
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),location.getLongitude()),17));
                    firstTime=false;
                }

            }
        };

        public void search_Bus_Location(String key){
            if(PerticulerMarker.containsKey(key)){
                String key2=PerticulerMarker.get(key);
                Marker marker=mNamedMarkers.get(key2);
                marker.showInfoWindow();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 30));
            }
            else {
                Toast.makeText(this, "Sorry No car found", Toast.LENGTH_SHORT).show();
            }

        }
        public void SetBusLocation(Users2 users2,String key){
            Location location = new Location("");
            location.setLatitude(users2.getLatitude());
            location.setLongitude(users2.getLongitude());
            location.setBearing(users2.getBaring());

            LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
            Marker marker = mNamedMarkers.get(key);
            if(marker==null){
                MarkerOptions markerOptions=getMarkerOptions(users2);
                markerOptions.position(latLng);
                int height = 80;// resize according to your zooming level
                int width = 30;// resize according to your zooming level
                BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.car3);
                Bitmap b=bitmapdraw.getBitmap();
                Bitmap finalMarker= Bitmap.createScaledBitmap(b, width, height, false);
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(finalMarker));
    //            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.car3));
                markerOptions.rotation(location.getBearing());
                markerOptions.anchor((float)0.5,(float)0.5);
                marker=mMap.addMarker(markerOptions);
                mNamedMarkers.put(key, marker);
                PerticulerMarker.put(users2.title,key);
                arr.add(users2.title);
            }else{
                marker.setPosition(latLng);
                marker.setRotation(location.getBearing());

            }

        }
        public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
            int width = bm.getWidth();
            int height = bm.getHeight();
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        }


       public void setUserLocation(Location location){
           LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());

            if(userMarker==null){
                MarkerOptions markerOptions=new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.car));
                //for rotation of car
                markerOptions.rotation(location.getBearing());
                markerOptions.anchor((float)0.5,(float)0.5);
                userMarker=mMap.addMarker(markerOptions);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
            }else{
                userMarker.setPosition(latLng);
                userMarker.setRotation(location.getBearing());

            }

        }

        private void startedLocationUpdate() {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "location permited", Toast.LENGTH_SHORT).show();
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);

            if(Mapview!=null && Mapview.findViewById(Integer.parseInt("1"))!=null){
                View location_but=((View)Mapview.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
                RelativeLayout.LayoutParams relativeLayout=(RelativeLayout.LayoutParams)location_but.getLayoutParams();
                relativeLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP,0);
                relativeLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
                relativeLayout.setMargins(0,0,40,180);
            }

    //        if location button off then it request to user to on location

            SettingsClient settingsClient = LocationServices.getSettingsClient(MapsActivity.this);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

            Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());

            task.addOnSuccessListener(MapsActivity.this, new OnSuccessListener<LocationSettingsResponse>() {
                @Override
                public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

                }
            });

            task.addOnFailureListener(MapsActivity.this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    if (e instanceof ResolvableApiException) {
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        try {
                            resolvable.startResolutionForResult(MapsActivity.this, 51);
                        } catch (IntentSender.SendIntentException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });


            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        }

        private   void stopLocationUpdate(){
            fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        }

                 @Override
                 protected void onStart() {
                     if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                         startedLocationUpdate();
                     }
                     else {
                         ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESSS_LOCATION_REQUEST_CODE);
                     }
                     super.onStart();
                 }

                 @Override
                 protected void onStop() {
                      stopLocationUpdate();
                     super.onStop();
                 }

                 private MarkerOptions getMarkerOptions(Users2 users2) {
            return new MarkerOptions().title(users2.title).snippet("bus");
        }
        protected  void init_navi(){
            bottomNavigationView = findViewById(R.id.bottom_nevigation);
            bottomNavigationView.setSelectedItemId(R.id.home);
            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    Intent intent;
                    switch (item.getItemId()) {
                        case R.id.home:
                            return true;
                        case R.id.book:
                            intent = new Intent(MapsActivity.this, MainActivity2.class);
                            startActivity(intent);
                            overridePendingTransition(0,0);
                            finish();
                            return true;
                        case R.id.profile:
                            intent = new Intent(MapsActivity.this, ProfileActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0,0);
                            finish();
                            return true;
                        case R.id.booking:
                            intent = new Intent(MapsActivity.this, RidesActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0,0);
                            finish();
                            return true;

                    }
                    return false;
                }
            });
        }

        @Override
        public boolean onMarkerClick(@NonNull @NotNull Marker marker) {
            Toast.makeText(this, marker.getTitle(), Toast.LENGTH_SHORT).show();
            String title=marker.getTitle();
            String titles[]=title.split("-");
                Bottom_view bottomSheetDialogFragment=new Bottom_view();
                bottomSheetDialogFragment.getValue(titles[0],titles[1]);
            bottomSheetDialogFragment.show(getSupportFragmentManager(),bottomSheetDialogFragment.getTag());
            return false;
        }
    }
}