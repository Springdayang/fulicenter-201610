package cn.ucai.fulicenter.controller.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.view.MFGT;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MFGT.startActivity(SplashActivity.this,MainActivity.class);
                MFGT.finishActivity(SplashActivity.this);
            }
        },2000);
    }
}
