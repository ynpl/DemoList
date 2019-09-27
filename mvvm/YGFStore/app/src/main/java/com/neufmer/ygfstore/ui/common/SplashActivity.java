package com.neufmer.ygfstore.ui.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.YGFApplication;
import com.neufmer.ygfstore.ui.main.MainActivity;

import java.lang.ref.WeakReference;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // 停留3秒
        MyHandler handler = new MyHandler(this);
        handler.sendEmptyMessageDelayed(0, 3 * 1000);
    }

    private static class MyHandler extends Handler {
        private WeakReference<SplashActivity> mActivityRef;

        public MyHandler(SplashActivity activity) {
            mActivityRef = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SplashActivity activity = mActivityRef.get();
            if (activity != null) {
                activity.goToNext();
            }
        }
    }

    private void goToNext() {
        if (!YGFApplication.checkLogin(this)) {
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }

}
