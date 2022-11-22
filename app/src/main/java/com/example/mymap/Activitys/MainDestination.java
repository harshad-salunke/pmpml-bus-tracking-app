package com.example.mymap.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.mymap.R;

public class MainDestination extends AppCompatActivity {
TextView MD_From;
TextView MD_to;
TextView MD_rupees;
TextView to_rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_destination);
        Intent intent=getIntent();
        String from=intent.getStringExtra("from");
        String to=intent.getStringExtra("to");
        String pay=intent.getStringExtra("pay");
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.bhagva));
        }

        MD_From=findViewById(R.id.from_destination);
        MD_to=findViewById(R.id.To_destination);
        MD_rupees=findViewById(R.id.md_rupees);
        to_rs=findViewById(R.id.rs);
        MD_rupees.setText("₹ "+pay);
        MD_to.setText(to);
        MD_From.setText(from);
        to_rs.setText("Pay : ₹ "+pay);
        Button  button=findViewById(R.id.pay_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainDestination.this, PaymentActivity.class);
                intent.putExtra("from",from);
                intent.putExtra("to",to);
                intent.putExtra("pay",pay);
                intent.putExtra("check",true);
                startActivity(intent);
            }
        });

    }
}