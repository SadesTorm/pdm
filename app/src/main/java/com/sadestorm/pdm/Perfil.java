package com.sadestorm.pdm;

public class Perfil {
    private String nome;
    private String endereco;
    private String email;
    private int idade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) throws Exception {

        if(idade < 18){
            throw new Exception("A idade deve ser maior que 18!");
        }else{
            this.idade = idade;
        }
    }
}
