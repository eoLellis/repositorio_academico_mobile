package com.example.projeto_uva;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormCadastro extends AppCompatActivity {

    private EditText edit_nome, edit_matricula, edit_senha;
    private Button bt_cadastrar;
    private FirebaseAuth mAuth;  // Firebase Authentication
    private FirebaseFirestore db;  // Firebase Firestore

    String[] mensagens = {"Preencha todos os campos!!", "Cadastro realizado com Sucesso!"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        // Inicializa o Firebase Auth e Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        IniciarComponentes();

        bt_cadastrar.setOnClickListener(view -> {
            String nome = edit_nome.getText().toString();
            String matricula = edit_matricula.getText().toString();
            String senha = edit_senha.getText().toString();

            if (nome.isEmpty() || matricula.isEmpty() || senha.isEmpty()) {
                Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.YELLOW);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
            } else {
                CadastrarUsuario(nome, matricula, senha);
            }
        });
    }

    // Método para cadastrar o usuário e salvar os dados no Firestore
    private void CadastrarUsuario(String nome, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            // Usuário cadastrado com sucesso, salvar informações no Firestore
                            salvarDadosUsuario(user.getUid(), nome, email);
                        }
                        Snackbar.make(findViewById(R.id.main), mensagens[1], Snackbar.LENGTH_SHORT)
                                .setBackgroundTint(Color.GREEN)
                                .setTextColor(Color.WHITE)
                                .show();
                    } else {
                        // Exibe o erro caso o cadastro falhe
                        Snackbar.make(findViewById(R.id.main), "Erro no cadastro: " + task.getException().getMessage(), Snackbar.LENGTH_LONG)
                                .setBackgroundTint(Color.RED)
                                .setTextColor(Color.WHITE)
                                .show();
                    }
                });
    }

    // Método para salvar os dados do usuário no Firestore
    private void salvarDadosUsuario(String userId, String nome, String email) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("nome", nome);
        userData.put("email", email);

        db.collection("users").document(userId)
                .set(userData)
                .addOnSuccessListener(aVoid -> {
                    // Dados salvos com sucesso
                    Snackbar.make(findViewById(R.id.main), "Dados salvos com sucesso!", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(Color.GREEN)
                            .setTextColor(Color.WHITE)
                            .show();
                })
                .addOnFailureListener(e -> {
                    // Erro ao salvar dados
                    Snackbar.make(findViewById(R.id.main), "Erro ao salvar os dados: " + e.getMessage(), Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.RED)
                            .setTextColor(Color.WHITE)
                            .show();
                });
    }

    private void IniciarComponentes() {
        edit_nome = findViewById(R.id.edit_nome);
        edit_matricula = findViewById(R.id.edit_matricula);
        edit_senha = findViewById(R.id.edit_senha);
        bt_cadastrar = findViewById(R.id.bt_cadastrar);
    }
}
