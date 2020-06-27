package com.example.demo;

public class ListaCidades{
	private Cidade prim;
	private Cidade ult;
	private int quantCidades;

	public ListaCidades(){
		this.prim = null;
		this.ult = null;
		this.quantCidades = 0;
	}

	public int getQuantCidades(){
		return this.quantCidades;
	}
	public Cidade getPrim(){
		return this.prim;
	}
	public Cidade getUlt(){
		return this.ult;
	}
	public void setQuantCidades(int CidadevoValor){
		this.quantCidades = CidadevoValor;
	}
	public void setPrim(Cidade CidadevoCidade){
		this.prim = CidadevoCidade;
	}
	public void setUlt(Cidade CidadevoCidade){
		this.ult = CidadevoCidade;
	}
	public boolean eVazia (){
		return (this.prim == null);
	}
	//insere uma Cidade
	public Cidade inserirUltimo (String nomeCidade){
		Cidade cidade = pesquisaCidadeNome(nomeCidade);
		if(cidade == null){
			cidade = new Cidade(nomeCidade);
			if (this.eVazia()){
				this.prim = cidade;
			} else {
				this.ult.setProx(cidade);
			}
			this.ult = cidade;
			this.quantCidades++;
		}	
		return cidade;
	}

	//insere uma Cidade sem criar nova
	public void inserirUltimo (Cidade cidade){
		if (this.eVazia()){
			this.prim = cidade;
		} else {
			this.ult.setProx(cidade);
		}
		this.ult = cidade;
		this.quantCidades++;
	}
	//retorna a cidade Desejada
	public Cidade pesquisarCidade (Cidade cidade){
		Cidade atual = this.prim;
		while ((atual != null) && (atual != cidade)){
			atual = atual.getProx();
		}	
		return atual;
    }
    
    public Cidade pesquisaCidadeNome (String nome){
		Cidade atual = this.prim;
		while ((atual != null) && (!atual.getNomeCidade().equals(nome))){
			atual = atual.getProx();
		}	
		return atual;
	}

	//remove um determinado nó em qualquer posição na lista.
	public boolean removerCidade (Cidade cidade) {
		Cidade atual = this.prim;
		Cidade ant = null;
		if (eVazia()){
			return false;
		} else {
			while ((atual != null)&&(atual != cidade)){
				ant = atual;
				atual = atual.getProx();
			}
			if (atual == null){
				return false;
			}
			else{
				if (atual == this.prim){
					if (this.prim == this.ult){
						this.ult = null;
					}
					this.prim = this.prim.getProx();
				} else {
					if (atual == this.ult){
						this.ult = ant;
					}
					ant.setProx(atual.getProx());
				}
				this.quantCidades--;
				return true;
			}
		}
	}


}
