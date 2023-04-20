package com.test.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.test.myproject.R;

public class FlashActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_TIMEOUT = 2400; // 2.4 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_screen);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(FlashActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN_TIMEOUT);

        ImageView logo = findViewById(R.id.logo);
        TextView name = findViewById(R.id.Myname);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.coder_name_animation);
        logo.startAnimation(animation);
        logo.setVisibility(View.VISIBLE);
        name.startAnimation(animation2);

    }
}
