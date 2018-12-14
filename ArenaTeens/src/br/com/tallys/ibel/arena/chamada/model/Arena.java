package br.com.tallys.ibel.arena.chamada.model;

import java.util.LinkedList;

public class Arena {
	
	public LinkedList<Lider> lideres;
	public LinkedList<GA> ga;
	public LinkedList<Fechamento> relatorios;
	
	



	public Arena(LinkedList<Lider> lideres, LinkedList<GA> ga, LinkedList<Fechamento> relatorios) {
		super();
		this.lideres = lideres;
		this.ga = ga;
		this.relatorios = relatorios;
	}


	public Arena() {
		// TODO Auto-generated constructor stub
		this.lideres = new LinkedList<Lider>();
		this.ga = ga = new LinkedList<GA>();
		this.relatorios = new LinkedList<Fechamento>();
	}


	public void setLiders(LinkedList<Lider> lds) {
		this.lideres = lds;		
	}


	public void setGAs(LinkedList<GA> gas) {
		this.ga = gas;		
	}


	public LinkedList<Lider> getLideres() {
		return lideres;
	}


	public void setLideres(LinkedList<Lider> lideres) {
		this.lideres = lideres;
	}


	public LinkedList<GA> getGa() {
		return ga;
	}


	public void setGa(LinkedList<GA> ga) {
		this.ga = ga;
	}


	public LinkedList<Fechamento> getRelatorios() {
		return relatorios;
	}


	public void setRelatorios(LinkedList<Fechamento> relatorios) {
		this.relatorios = relatorios;
	}	
	
	
	
}
