package com.example.projeto_uva;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tela_Inicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_inicial);

        // Configura o Toolbar como barra de ação
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Ajusta os insets para edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar os botões de matéria conforme antes
        // Botões para matérias
        findViewById(R.id.button_materia1).setOnClickListener(view -> startActivity(new Intent(this, Materia1.class)));
        findViewById(R.id.button_materia2).setOnClickListener(view -> startActivity(new Intent(this, Materia2.class)));
        findViewById(R.id.button_materia3).setOnClickListener(view -> startActivity(new Intent(this, Materia3.class)));
        findViewById(R.id.button_materia4).setOnClickListener(view -> startActivity(new Intent(this, Materia4.class)));
        findViewById(R.id.button_materia5).setOnClickListener(view -> startActivity(new Intent(this, Materia5.class)));
        findViewById(R.id.button_materia6).setOnClickListener(view -> startActivity(new Intent(this, Materia6.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla o menu no Toolbar
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_profile) {
            // Abre a tela de perfil quando o item do menu é clicado
            Intent intent = new Intent(Tela_Inicial.this, TelaPerfil.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
