package com.sadestorm.pdm;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth usuario = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText emailTxt = findViewById(R.id.emailTxt);
        final EditText senhaTxt = findViewById(R.id.senhaTxt);

        Button loginBtn = findViewById(R.id.loginBtn);

        Button esqueceuSenha = findViewById(R.id.cliqueAquiBtn);

        Button cadastre = findViewById(R.id.cadastroBtn);





        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login(emailTxt.getText().toString(), senhaTxt.getText().toString(), MainActivity.this);

            }
        });

        esqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastro.class);
                startActivity(intent);
            }
        });

        cadastre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastro.class);
                startActivity(intent);
            }
        });



    }

    public void login(String email, String senha, final Activity activity) { // METODO E TRATAMENTO PARA LOGIN E AUTENTICAÇÃO
        final FirebaseAuth autentica = FirebaseAuth.getInstance();
        Task<AuthResult> task = autentica.signInWithEmailAndPassword(email, senha); // METODO ONDE DO FIREBASE QUE REALISA A AUTENTICAÇÃO NO BANCO DE DADOS
        task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = autentica.getCurrentUser();
                    Toast.makeText(activity, user.getUid() + "/" + user.getEmail(), Toast.LENGTH_LONG).show();// EXIBE MESSAGEM NA TELA DO MEAIL  AUTENTICADO
                    Intent i = new Intent(activity, Tela1.class);
                    startActivity(i);
                } else {
                    Toast.makeText(activity, "Falha na autenticaçao", Toast.LENGTH_LONG).show();
                }
            }
        });
    }






 /* Verifica usuario logado \\

        if (usuario.getCurrentUser() != null) {
            Log.i("CriandoUsuario", "Usuario Logado!");
        } else {
            Log.i("CriandoUsuario", "Usuario nao Logado!");
        }
        */


  /*  // Cadastrando usuario \\

        usuario.createUserWithEmailAndPassword("paulo2@gmail.com","paulo123")
        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.i("CriandoUsuario", "Usuario cadastrado com sucesso!");
                }else{
                    Log.i("CriandoUsuario", "Erro Usuario nao cadastrado!");
                }
            }
        });*/



}

