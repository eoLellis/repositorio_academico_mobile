package com.example.projeto_uva;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class FormLogin extends AppCompatActivity {

    private EditText edit_email, edit_senha;
    private Button bt_entrar;
    private TextView text_tela_cadastro;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    String[] mensagens = {"Preencha todos os campos!", "Login realizado com sucesso!"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        IniciarComponentes();

        bt_entrar.setOnClickListener(view -> {

            String email = edit_email.getText().toString();
            String senha = edit_senha.getText().toString();

            if (email.isEmpty() || senha.isEmpty()) {
                Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.YELLOW);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
            } else {
                LogarAluno(email, senha);
            }
        });

        text_tela_cadastro.setOnClickListener(view -> {
            Intent intent = new Intent(FormLogin.this, FormCadastro.class);
            startActivity(intent);
        });
    }

    private void LogarAluno(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser aluno = mAuth.getCurrentUser();
                        if (aluno != null) {
                            String aluno_id = aluno.getUid();
                            DocumentReference docRef = db.collection("alunos").document(aluno_id);
                            Aluno alunoData = new Aluno(email);
                            ((DocumentReference) docRef).set(alunoData).addOnSuccessListener(aVoid -> {
                                Snackbar.make(findViewById(R.id.main), mensagens[1], Snackbar.LENGTH_SHORT).setBackgroundTint(Color.GREEN).setTextColor(Color.WHITE).show();
                                Intent intent = new Intent(FormLogin.this, Tela_Inicial.class);
                                startActivity(intent);
                                finish();
                            }).addOnFailureListener(e -> {
                                Snackbar.make(findViewById(R.id.main), "Erro ao salvar dados: " + e.getMessage(), Snackbar.LENGTH_LONG).setBackgroundTint(Color.RED).setTextColor(Color.WHITE).show();
                            });
                        }
                    } else {
                        Snackbar.make(findViewById(R.id.main), "Erro no login: " + Objects.requireNonNull(task.getException()).getMessage(), Snackbar.LENGTH_LONG).setBackgroundTint(Color.RED).setTextColor(Color.WHITE).show();
                    }
                });
    }

    private void IniciarComponentes() {
        edit_email = findViewById(R.id.edit_matricula);
        edit_senha = findViewById(R.id.edit_senha);
        bt_entrar = findViewById(R.id.bt_entrar);
        text_tela_cadastro = findViewById(R.id.text_tela_cadastro);
    }

    public static class Aluno {
        private String email;

        public Aluno() {
        }

        public Aluno(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}