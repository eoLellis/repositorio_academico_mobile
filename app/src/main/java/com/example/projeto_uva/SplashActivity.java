package com.example.projeto_uva;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Usando o Handler com o Looper da thread principal
        Handler handler = new Handler(Looper.getMainLooper());

        // Timer para exibir a Splash Screen
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, FormLogin.class);
            startActivity(intent);
            finish(); // Encerra a Splash Screen
        }, 1000); // Exibe a Splash por 2 segundos
    }
}