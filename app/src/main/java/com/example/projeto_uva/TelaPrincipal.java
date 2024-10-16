package com.example.projeto_uva;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

public class TelaPrincipal extends AppCompatActivity {

    private TextView textNomeUser, textMatriculaUser;
    private Button btDeslogar;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;  // Firestore

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        IniciarComponentes();

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();  // Inicializa o Firestore

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            // Busca os dados do usuário no Firestore
            DocumentReference docRef = db.collection("users").document(user.getUid());
            docRef.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    // Obtém os dados do Firestore
                    String nome = documentSnapshot.getString("nome");
                    String matricula = documentSnapshot.getString("email");

                    // Atualiza a interface com os dados do Firestore
                    textNomeUser.setText(nome != null ? nome : "Usuário");
                    textMatriculaUser.setText(matricula);
                } else {
                    // Se não encontrar o documento, usar valores padrão
                    textNomeUser.setText("Usuário padrão");
                    textMatriculaUser.setText("Matrícula padrão");
                }
            });
        }

        btDeslogar.setOnClickListener(view -> {
            mAuth.signOut();
            Intent intent = new Intent(TelaPrincipal.this, FormLogin.class);
            startActivity(intent);
            finish();  // Encerra a TelaPrincipal
        });
    }

    private void IniciarComponentes() {
        textNomeUser = findViewById(R.id.text_nome_user);
        textMatriculaUser = findViewById(R.id.text_matricula_user);
        btDeslogar = findViewById(R.id.bt_deslogar);
    }
}
