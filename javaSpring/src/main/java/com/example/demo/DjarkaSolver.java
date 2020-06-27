package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class DjarkaSolver {

    private List<Controle> mapaDistancias;
    private ListaCidades cidades;
    private ListaCidades cidadesNaoVisitadasLista;
    private ListaRuas ruasLista;

    public DjarkaSolver(ListaCidades cidades, ListaRuas ruasLista) {
        this.setCidades(cidades);
        this.setRuasLista(ruasLista);
    }

    public List<Controle> solve(){

        Cidade cidadeA = cidades.getPrim();
        buildShortPathMap(cidadeA);

        return mapaDistancias;
    }

    public void buildShortPathMap(Cidade cidade) {
        cidadesNaoVisitadasFactory(cidade);
        controleFactory(cidade);
        Cidade cidadeAtual = cidade;
        while(!cidadesNaoVisitadasLista.eVazia() && cidadeAtual != null){
            updateVizinhosDistancia(cidadeAtual);
            cidadeAtual = getProxTavbela();
        }
        
    }

    public Cidade getProxTavbela(){
        Cidade prox =  cidadesNaoVisitadasLista.getPrim();
        Cidade cidadePonteiro = cidadesNaoVisitadasLista.getPrim();
        
        while(cidadePonteiro != null){
            Cidade next = cidadePonteiro;
            Controle controleAtual = getControle(prox);
            Controle proxControle = getControle(next);

            if(controleAtual.getDistanciaMin() > proxControle.getDistanciaMin()){
                prox = next;
            }  
            cidadePonteiro = cidadePonteiro.getProx();
        }

        return prox;
    }

    public void updateVizinhosDistancia(Cidade cidadeAtual){
        Controle controleAtual = getControle(cidadeAtual);
        cidadesNaoVisitadasLista.removerCidade(cidadeAtual);
       
        Rua ruaPonteiro = ruasLista.getPrim();
        while(ruaPonteiro!= null){
            Cidade vizinho = ruaPonteiro.getVizinho(cidadeAtual);
            if(vizinho != null && cidadesNaoVisitadasLista.pesquisarCidade(vizinho) != null){
                Controle controleVizinho = getControle(vizinho);
                if(controleVizinho.getDistanciaMin() > controleAtual.getDistanciaMin() + ruaPonteiro.getDistancia()){
                    controleVizinho.setCidadeAnte(cidadeAtual);
                    controleVizinho.setDistanciaMin(controleAtual.getDistanciaMin() + ruaPonteiro.getDistancia());
                }
            }
            ruaPonteiro = ruaPonteiro.getProx();
        }

    }

    public Controle getControle(Cidade cidade){
        for (int i = 0; i < mapaDistancias.size(); i++) {
            Controle localControle = mapaDistancias.get(i);
            if(localControle.getCidadeAlvo() == cidade)
                return localControle;
        }

        return null;
    }

    public void controleFactory(Cidade cidadeInicial) {
        mapaDistancias = new ArrayList<Controle>();
        Cidade cidadePonteiro = cidades.getPrim();
       
        while(cidadePonteiro != null){
            Controle controle = new Controle();
            controle.setCidadeAlvo(cidadePonteiro);
            if(cidadePonteiro == cidadeInicial)
                controle.setDistanciaMin(0.0);
            mapaDistancias.add(controle);   
            cidadePonteiro = cidadePonteiro.getProx();
        }

    }

    public void cidadesNaoVisitadasFactory(Cidade cidadeInicial) {
        cidadesNaoVisitadasLista = new ListaCidades();
        Cidade cidade = cidades.getPrim();
        while(cidade != null){
            if(cidade != cidadeInicial)
                cidadesNaoVisitadasLista.inserirUltimo(cidade);
            cidade = cidade.getProx();
        }
    }

    public ListaCidades getCidades() {
        return cidades;
    }

    public void setCidades(ListaCidades cidades) {
        this.cidades = cidades;
    }

    public ListaRuas getRuasLista() {
        return ruasLista;
    }

    public void setRuasLista(ListaRuas ruasLista) {
        this.ruasLista = ruasLista;
    }


}