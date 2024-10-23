package com.example.projeto_uva;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

public class Tela_Inicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_inicial);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton buttonProfile = findViewById(R.id.button_profile);
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cria o Intent para redirecionar para a Tela de perfil
                Intent intent = new Intent(Tela_Inicial.this, TelaPrincipal.class);
                startActivity(intent);  // Inicia a nova Activity
            }
        });

        ImageButton ButtonMateria1 = findViewById(R.id.button_materia1);
        ButtonMateria1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_Inicial.this, Materia1.class);
            }
        });
        ImageButton ButtonMateria2 = findViewById(R.id.Materia2);
        ButtonMateria1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_Inicial.this, Materia2.class);
            }
        });
        ImageButton ButtonMateria3 = findViewById(R.id.Materia3);
        ButtonMateria1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_Inicial.this, Materia3.class);
            }
        });
        ImageButton ButtonMateria4 = findViewById(R.id.Materia4);
        ButtonMateria1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_Inicial.this, Materia4.class);
            }
        });
        ImageButton ButtonMateria5 = findViewById(R.id.Materia5);
        ButtonMateria1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_Inicial.this, Materia5.class);
            }
        });
        ImageButton ButtonMateria6 = findViewById(R.id.Materia6);
        ButtonMateria1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_Inicial.this, Materia6.class);
            }
        });
    }
}