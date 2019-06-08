package com.wiselap.accounts;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.wiselap.accounts.databinding.ActivitySplashScreenBinding;

import com.wiselap.accounts.SignIn.GoogleSignInUsersActivity;

public class SplashScreen extends AppCompatActivity {
    ActivitySplashScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_splash_screen);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.splash);
        binding.text.startAnimation(animation);
        final Intent intent=new Intent(this, GoogleSignInUsersActivity.class);
        Thread thread=new Thread(){
            public void run(){
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }

        }; thread.start();
    }
}
