package com.example.mymap.Activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mymap.Login.Login_info;
import com.example.mymap.R;

public class SplashActivity extends AppCompatActivity {
    TextView textView;
    Animation animation;
    ImageView aniimage;
    Animation animationimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.yellow));
        }

        textView=findViewById(R.id.splash_text);
        aniimage=findViewById(R.id.ani_image);
        animationimage=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.image);
        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_text);
        textView.setAnimation(animation);
        aniimage.setAnimation(animationimage);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this, Login_info.class);
                startActivity(intent);
                finish();
            }
        },1000);

    }
}