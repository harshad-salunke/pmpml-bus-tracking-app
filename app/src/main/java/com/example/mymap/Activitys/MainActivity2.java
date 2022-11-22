package com.example.mymap.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mymap.Adapters.MainStationAdapter;
import com.example.mymap.Model.Main_station;
import com.example.mymap.R;
import com.example.mymap.databinding.ActivityMain2Binding;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
     ActivityMain2Binding binding;
     ArrayList<String> from_arr;
     ArrayList<String> to_arr;
     ArrayAdapter<String> from_adapter;
     ArrayAdapter<String> to_adapter;
    MainStationAdapter destinationsAtapter;
    ArrayList<Main_station> arrayList=new ArrayList<>();
    RecyclerView recyclerView;
ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.gray));
        }
        init_navi();
        shimmerFrameLayout=findViewById(R.id.booking_shimmer);
        shimmerFrameLayout.startShimmer();

         recyclerView=findViewById(R.id.ApiList);
         destinationsAtapter=new MainStationAdapter(this,arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(destinationsAtapter);
//        from search bar modle

        from_arr=new ArrayList<>();
        from_adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,from_arr);
        binding.placeList.setAdapter(from_adapter);
        binding.from.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                binding.placeList.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equals("")){
                    binding.placeList.setVisibility(View.GONE);
                }
                else {
                    binding.placeList.setVisibility(View.VISIBLE);
                }
                from_adapter.getFilter().filter(newText);
                filter(newText);
                return false;
            }
        });

        to_arr=new ArrayList<>();
        to_adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,to_arr);
        binding.placeList2.setAdapter(to_adapter);
        binding.to.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                binding.placeList2.setVisibility(View.GONE);
                Toast.makeText(MainActivity2.this, query, Toast.LENGTH_SHORT).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equals("")){
                    binding.placeList2.setVisibility(View.GONE);
                }
                else {
                    binding.placeList2.setVisibility(View.VISIBLE);
                }
                to_adapter.getFilter().filter(newText);
                filter(newText);

                return false;
            }
        });


        binding.placeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object clickedItem=parent.getAdapter().getItem(position);
                binding.from.setQuery(clickedItem.toString(),true);
            }
        });

        binding.placeList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object clickedItem=parent.getAdapter().getItem(position);
                binding.to.setQuery(clickedItem.toString(),true);
            }
        });



        RetriveDataFromDB();

    }

    public void RetriveDataFromDB(){

//      calling api to showing data in recyclerView

        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(this);
        String url = "https://stormy-caverns-98061.herokuapp.com/";
        JsonArrayRequest
                jsonArrayRequest
                = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener() {

                    @Override
                    public void onResponse(Object response) {
                        try {
                            Log.d("apidata",response.toString());

                            JSONArray jArray = new JSONArray(response.toString());
                            JSONArray jsonArray2=jArray.getJSONArray(0);

                            Log.d("apidata2",jsonArray2.toString());
                            Log.d("lengthis",jsonArray2.length()+"");
                            for(int i=0;i<jsonArray2.length();i++){
                                JSONObject jsonObject=jsonArray2.getJSONObject(i);
                                String from=jsonObject.getString("from");
                                String to=jsonObject.getString("to");
                                if(to==null){
                                    to="null";
                                }
                                if (from==null){
                                    from=null;
                                }
                                from_arr.add(from);
                                to_arr.add(to);
                                arrayList.add(new Main_station(from,to));
                            }
                            shimmerFrameLayout.stopShimmer();
                            recyclerView.setVisibility(View.VISIBLE);
                            shimmerFrameLayout.setVisibility(View.GONE);
                            destinationsAtapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity2.this, "done", Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            Log.d("harshad",e.getLocalizedMessage());
                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.d("harshad",error.getLocalizedMessage());

                        Toast.makeText(MainActivity2.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);

    }

    protected  void init_navi(){
        bottomNavigationView = findViewById(R.id.bottom_nevigation);
        bottomNavigationView.setSelectedItemId(R.id.book);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.home:
                        intent = new Intent(MainActivity2.this, Bottom_view.MapsActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.book:
                        return true;
                    case R.id.profile:
                        intent = new Intent(MainActivity2.this, ProfileActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.booking:
                        intent = new Intent(MainActivity2.this, RidesActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }

    private void filter(String text) {
        ArrayList<Main_station> filteredlist = new ArrayList<>();

        for (Main_station item : arrayList) {

            if (item.getFrom().toLowerCase().contains(text.toLowerCase())) {

                filteredlist.add(item);
            }

        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "Sorry Result not found", Toast.LENGTH_SHORT).show();
        } else {
            destinationsAtapter.filterList(filteredlist);
        }
    }
}