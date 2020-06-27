package com.example.demo;

public class ListaRuas {
	private Rua prim;
	private Rua ult;
	private int quantRuas;

	public ListaRuas(){
		this.prim = null;
		this.ult = null;
		this.quantRuas = 0;
	}

	public int getQuantRuas(){
		return this.quantRuas;
	}
	public Rua getPrim(){
		return this.prim;
	}
	public Rua getUlt(){
		return this.ult;
	}
	public void setQuantRuas(int RuavoValor){
		this.quantRuas = RuavoValor;
	}
	public void setPrim(Rua RuavoRua){
		this.prim = RuavoRua;
	}
	public void setUlt(Rua RuavoRua){
		this.ult = RuavoRua;
	}
	public boolean eVazia (){
		return (this.prim == null);
	}
	//insere um Ruavo nó Rua final da lista ou se a lista estiver vazia, insere o primeiro nó na lista
	public void inserirUltimo (Cidade cidadeA, Cidade cidadeB, Double dist){
		Rua rua = pesquisarRua(cidadeA, cidadeB);
		if(rua == null){
			rua = new Rua(cidadeA, cidadeB, dist);
			if (this.eVazia()){
				this.prim = rua;
			} else {
				this.ult.setProx(rua);
			}
			this.ult = rua;
			this.quantRuas++;
		}
		
	}
	//retorna a rua que contem as duas cidades pesquisadas
	public Rua pesquisarRua (Cidade cidadeA, Cidade cidadeB){
		Rua atual = this.prim;
		while ((atual != null) && (!atual.contaisCidades(cidadeA, cidadeB))){
			atual = atual.getProx();
		}	
		return atual;
	}
    
    @Override
	public String toString(){
		String msg = "";
		Rua atual = this.prim;
		while (atual != null){
            msg += "Rua 1: " + atual.getCidade1().getNomeCidade() +
            ", Rua 2: " + atual.getCidade2().getNomeCidade()+
            "Distacia mínima: "+ atual.getDistancia().toString() +"\n" ;
			atual = atual.getProx();
		}
		return msg;
	}
	
}
