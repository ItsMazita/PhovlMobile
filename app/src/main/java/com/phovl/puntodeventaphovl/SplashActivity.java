package com.phovl.puntodeventaphovl;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Abrimos MainActivity después de 3 segundos
        new android.os.Handler().postDelayed(() -> {
            startActivity(new Intent(this, RegistroActivity.class));
            finish();
        }, 3000);
    }
}
