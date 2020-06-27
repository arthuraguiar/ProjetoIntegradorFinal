package com.example.demo;

import com.google.gson.annotations.Expose;

public class Cidade {

    @Expose
    private String nomeCidade;
    
    private Cidade prox;

    public Cidade(String nome) {
        this.nomeCidade = nome;
        this.prox = null;
    }

    public Cidade getProx() {
        return prox;
    }

    public void setProx(Cidade prox) {
        this.prox = prox;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }
}