package com.example.demo;

import java.util.List;

import org.json.simple.JSONObject;

public class DataFactory {
    ListaCidades listaCidades;
    ListaRuas listaRuas;

    public DataFactory(){
        this.listaCidades = new ListaCidades();
        this.listaRuas = new ListaRuas();
    }

    public void fetchDataFromjson(List<JSONObject> jsonArray){
        try{
            
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject rua = jsonArray.get(i);
                String cidade1Nome = (String) rua.get("Cidade1");
                String cidade2Nome = (String) rua.get("Cidade2");
                Cidade cidade1 = listaCidades.inserirUltimo(cidade1Nome);
                Cidade cidade2 = listaCidades.inserirUltimo(cidade2Nome);
                Double dist = (Double) rua.get("Distancia");
                listaRuas.inserirUltimo(cidade1, cidade2, dist);
            
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}