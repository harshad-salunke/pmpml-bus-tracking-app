package com.example.mymap.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.mymap.Adapters.Sub_Station_adapter;
import com.example.mymap.Model.SubStation_model;
import com.example.mymap.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

public class SubStationActivity extends AppCompatActivity {
ArrayList<SubStation_model> arrayList;
Sub_Station_adapter sub_station_adapter;
RecyclerView recyclerView;
ShimmerFrameLayout shimmerFrameLayout;
TextView sub_from;
TextView sub_to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_station);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.gray));
        }
        recyclerView=findViewById(R.id.sub_station);
        shimmerFrameLayout=findViewById(R.id.shimmer_view_container);
        sub_from=findViewById(R.id.sub_from);
        sub_to=findViewById(R.id.sub_to);

        Intent intent=getIntent();
        String from=intent.getStringExtra("from");
        String to=intent.getStringExtra("to");
        sub_from.setText(from);
        sub_to.setText(to);

        shimmerFrameLayout.startShimmer();
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


sub_station_adapter=new Sub_Station_adapter(this,arrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(sub_station_adapter);
        sub_station_adapter.notifyDataSetChanged();

//        Swargat
//        Laxmi Narayan Theatre
//        S.T.Colony (Panchami Hotel) Brts
//        Bhapkar Petrol Pump Brts
//        Aranyeshwar Corner Brts
//        Natu Baug Brts
//        Padmavati Brts
//        Ahilyadevi Holkar Chowk (K.K Market) Brts
//        Balajinagar Brts
//        Bharti Vidyapeeth Brts
//        Katraj Dairy-Sarpodyan Brts
//        More Baug Brts
//                Katraj


    }
}