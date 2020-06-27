package com.example.demo;

import com.google.gson.annotations.Expose;

public class Controle {
    @Expose
    private Cidade cidadeAlvo = null;

    @Expose
    private Cidade cidadeAnte = null;

    @Expose
    private Double distanciaMin = Double.MAX_VALUE;

    public Cidade getCidadeAlvo() {
        return cidadeAlvo;
    }

    public Double getDistanciaMin() {
        return distanciaMin;
    }

    public void setDistanciaMin(Double distanciaMin) {
        this.distanciaMin = distanciaMin;
    }

    public Cidade getCidadeAnte() {
        return cidadeAnte;
    }

    public void setCidadeAnte(Cidade cidadeAnte) {
        this.cidadeAnte = cidadeAnte;
    }

    public void setCidadeAlvo(Cidade cidadeAlvo) {
        this.cidadeAlvo = cidadeAlvo;
    }
}