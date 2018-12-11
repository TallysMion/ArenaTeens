package br.com.tallys.ibel.arena.chamada.model;

import java.util.LinkedList;

public class Arena {
	
	LinkedList<Lider> lideres;
	LinkedList<GA> ga;
	
	
	public Arena(LinkedList<Lider> lideres, LinkedList<GA> ga) {
		super();
		this.lideres = lideres;
		this.ga = ga;
	}


	public Arena() {
		// TODO Auto-generated constructor stub
	}


	public void setLiders(LinkedList<Lider> lds) {
		this.lideres = lds;		
	}


	public void setGAs(LinkedList<GA> gas) {
		this.ga = gas;		
	}	
	
}
