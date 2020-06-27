package com.example.demo;

public class Rua {
    private Cidade cidade1;
    private Cidade cidade2;
    private Rua prox;
    private Double distancia;

    public Cidade getVizinho(Cidade cidade) {
        if (cidade1 == cidade) {
            return cidade2;
        } else if (cidade2 == cidade) {
            return cidade1;
        }

        return null;
    }

    public boolean contaisCidades(Cidade cidadeA, Cidade cidadeB){
        return (cidade1 == cidadeA && cidade2 ==  cidadeB||(cidade1 == cidadeB && cidade2 ==  cidadeA));
    }

    public Rua getProx() {
        return prox;
    }

    public void setProx(Rua prox) {
        this.prox = prox;
    }

    public Rua(Cidade cidade1, Cidade cidade2, Double distancia) {
        this.cidade1 = cidade1;
        this.cidade2 = cidade2;
        this.distancia = distancia;
        this.prox = null;
    }

    public Cidade getCidade1() {
        return cidade1;
    }
    public void setCidade1(final Cidade cidade1) {
        this.cidade1 = cidade1;
    }

    public Cidade getCidade2() {
        return cidade2;
    }

    public void setCidade2(final Cidade cidade2) {
        this.cidade2 = cidade2;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }
}