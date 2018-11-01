package br.com.tallys.ibel.arena.chamada.model;

import java.util.LinkedList;

public class Teen extends User{
	
	private int id;
	private String nome;
	private String responsavel;
	private String Telefone;
	private String TelResponsavel;
	private int lider;
	private LinkedList<RelatorioArena> relatorios;
	private LinkedList<RelatorioImersao> imersao;
	
	
	@Override
	public String constructHTML() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toHTML() {
		// TODO Auto-generated method stub
		return null;
	}

}
