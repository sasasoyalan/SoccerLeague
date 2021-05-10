package com.sssoyalan.soccerleague.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.sssoyalan.soccerleague.R;

public class SplashActivty extends AppCompatActivity {

    private final Handler handler = new Handler();
    private ProgressBar paginationProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = findViewById(R.id.splash_image);
        ConstraintLayout cl = findViewById(R.id.splash_cl);
        paginationProgressBar = findViewById(R.id.paginationProgressBar);
        paginationProgressBar.setVisibility(View.VISIBLE);
        Animation animation_top = AnimationUtils.loadAnimation(this, R.anim.splash_anim_top);
        Animation animation_bottom = AnimationUtils.loadAnimation(this, R.anim.splash_anim_bottom);
        imageView.setAnimation(animation_top);
        cl.setAnimation(animation_bottom);
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!isFinishing()) {
                paginationProgressBar.setVisibility(View.INVISIBLE);
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable,3500);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}