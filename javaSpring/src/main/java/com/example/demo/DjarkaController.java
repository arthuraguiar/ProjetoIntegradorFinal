package com.example.demo;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DjarkaController {
    
    @GetMapping("/ping")
    public ResponseEntity<String> find(){     
        return  ResponseEntity.ok("{\"success\":1}");
    }

    @PostMapping(path = "/solve/{cidadeorigem}")
	@ResponseBody
	public ResponseEntity<String> create( @PathVariable("cidadeorigem") String cidadeOrigem, @RequestBody List<JSONObject> jsonArray){
        DataFactory dataFactory = new DataFactory();
        dataFactory.fetchDataFromjson(jsonArray);

        List<Controle> result = new DjarkaSolver(dataFactory.listaCidades, dataFactory.listaRuas).solve();
        JsonArray teste = factory(result);
        return  ResponseEntity.ok(teste.toString());
    }

    private JsonArray factory(List<Controle> controles){
        JsonArray jArray = new  JsonArray();

        for (Controle controle : controles) {
            JsonObject obj = new JsonObject();
            obj.addProperty("Cidade", controle.getCidadeAlvo().getNomeCidade());
            if(controle.getCidadeAnte() != null)
                obj.addProperty("CidadeAnterior", controle.getCidadeAnte().getNomeCidade());
            obj.addProperty("DistanciaMinima", controle.getDistanciaMin());
            jArray.add(obj);
        }

        return jArray;
    }
}