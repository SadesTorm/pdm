package com.sadestorm.pdm;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Cadastro extends AppCompatActivity {

    EditText emailTxt;
    EditText nomeTxt;
    EditText enderecoTxt;
    EditText idadeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        emailTxt = findViewById(R.id.emailTxt);
        nomeTxt = findViewById(R.id.nomeTxt);
        enderecoTxt = findViewById(R.id.enderecoTxt);
        idadeTxt = findViewById(R.id.idadeTxt);

        Button gravarBtn = findViewById(R.id.gravarBtn);

        gravarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    gravar(emailTxt.getText().toString(),enderecoTxt.getText().toString(),nomeTxt.getText().toString(),idadeTxt.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void load(){
        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
        String email = usuario.getEmail();

        emailTxt.setText(email); // perguntar o professor como travar ovalor la na cAIXA

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("Usuario");
        Query consulta = ref.orderByChild("email").equalTo(email);

        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Perfil p = dataSnapshot.getChildren().iterator().next().getValue(Perfil.class);
                enderecoTxt.setText(p.getEndereco());
                nomeTxt.setText(p.getNome());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void gravar(String email, String ende, String nome, String idade) throws Exception {
        Perfil p = new Perfil();
        p.setEmail(email);
        p.setEndereco(ende);
        p.setIdade(Integer.parseInt(idade));
        p.setNome(nome);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("Usuario");

        ref.push().setValue(p);
    }

}
