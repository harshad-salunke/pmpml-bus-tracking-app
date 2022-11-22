package com.example.mymap.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymap.Model.Booking_model;
import com.example.mymap.Model.UserSingleton;
import com.example.mymap.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {
String from;
String to;
String rupess;
ImageButton cancel_btn;
Booking_model  booking_model;
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
Boolean check=false;
String time="";
String data="";
String ticket_no="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.grenn));
        }
        cancel_btn=findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent=getIntent();
        from=intent.getStringExtra("from");
        to=intent.getStringExtra("to");
        rupess=intent.getStringExtra("pay");
        time=intent.getStringExtra("time");
        data=intent.getStringExtra("date");
        ticket_no=intent.getStringExtra("ticket_no");
        check=intent.getBooleanExtra("check",false);
        if (check){
            booking_model=new Booking_model(from,to,"c123","","",rupess);
            firebaseDatabase=FirebaseDatabase.getInstance();
            databaseReference=firebaseDatabase.getReference().child("ticket_book");
            startPayment();
        }
      else{
          Booking_model booking_model=new Booking_model(from,to,ticket_no,data,time,rupess);
          SetDataON_View(booking_model);
        }




    }
    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */

        final Checkout co = new Checkout();
        co.setKeyID("rzp_test_DDDjy3Dnb4MdpS");
        try {
            JSONObject options = new JSONObject();
            options.put("name", "PMPML Ltd");
            options.put("description", "After paying you can not cancel ticket.");
            options.put("send_sms_hash",true);
            options.put("allow_rotation", true);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://upload.wikimedia.org/wikipedia/en/7/7e/PMPML_logo.jpg?20130630054038");
            options.put("currency", "INR");
            int rupees=Integer.parseInt(rupess);
            long amount=rupees*100;
            options.put("amount", amount);

            JSONObject preFill = new JSONObject();
            preFill.put("email", "harshadsalunke@2002.com");
            preFill.put("contact", "9890931031");

            options.put("prefill", preFill);

            co.open(this, options);
        } catch (Exception e) {
            Toast.makeText(this, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm:ss a");
        String strDate = sdf.format(c.getTime());
        String strTime=formatDate.format(c.getTime());
        booking_model.setDate(strDate);
        booking_model.setTime(strTime);
        SetInDatabase(booking_model);
        try {
            Toast.makeText(this, "Payment Successful: " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("harhad", "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int i, String s) {
        finish();
        try {
            Toast.makeText(this, "Payment fail: " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("jarshad", "Exception in onPaymentSuccess", e);
        }
    }

    public void SetDataON_View(Booking_model booking_model){
        UserSingleton user=UserSingleton.getInstance("","","","");

        TextView gotolist=findViewById(R.id.go_to_booking);
        gotolist.setClickable(true);
        gotolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PaymentActivity.this, RidesActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
        TextView name=findViewById(R.id.Bname);
        TextView booking_date=findViewById(R.id.Bdate_no);
        TextView adharno=findViewById(R.id.Badhar_no);
        TextView from_text=findViewById(R.id.Bfrom);
        TextView to_text=findViewById(R.id.Bto_no2);
        TextView date2=findViewById(R.id.Bdate_no2);
        TextView time=findViewById(R.id.Btime_no2);
        TextView total=findViewById(R.id.Btotal_no2);

        name.setText(user.name);
        booking_date.setText(booking_model.getDate());
        adharno.setText(user.adharno);
        from_text.setText(booking_model.getFrom());
        to_text.setText(booking_model.getTo());
        date2.setText(booking_model.getDate());
        time.setText(booking_model.getTime());
        total.setText("₹ "+booking_model.getTotal());


    }
    public  void SetInDatabase(Booking_model booking_model){
        UserSingleton user=UserSingleton.getInstance("","","","");
        databaseReference.child(user.uid).push().setValue(booking_model).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(PaymentActivity.this, "Thank You", Toast.LENGTH_SHORT).show();
            }
        });
        SetDataON_View(booking_model);
    }
}