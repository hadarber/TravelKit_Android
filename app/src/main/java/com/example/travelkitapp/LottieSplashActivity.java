package com.example.travelkitapp;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.travelkitapp.auth.SignInActivity;

public class LottieSplashActivity extends AppCompatActivity {
    private LottieAnimationView earth_LOTTIE_animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_splash);

        findViews();
        earth_LOTTIE_animation.resumeAnimation();

        earth_LOTTIE_animation.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                TransactToMainActivity();

            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {

            }
        });
    }

    private void TransactToMainActivity() {
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }

    private void findViews() {
        earth_LOTTIE_animation = findViewById(R.id.earth_LOTTIE_animation);

    }
}