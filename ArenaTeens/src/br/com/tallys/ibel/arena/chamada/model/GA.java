package br.com.tallys.ibel.arena.chamada.model;

import java.util.LinkedList;

public class GA {
	
	private String nome; 
	private LinkedList<Sublider> sublideres;
	private LinkedList<Teens> teens;
	
	public void update(String nome, LinkedList<Sublider> sublds, LinkedList<Teens> teens) {
		this.nome = nome;
		this.sublideres = sublds;
		this.teens = teens;		
	}
	
}
