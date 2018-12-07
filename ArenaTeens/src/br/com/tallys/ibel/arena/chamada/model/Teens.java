package br.com.tallys.ibel.arena.chamada.model;

import java.util.LinkedList;

import br.com.tallys.ibel.arena.chamada.model.Enum.UserType;

public class Teens extends User {

	private GA grupo;
	private LinkedList<Relatorio> relatorios;
	
	//Desenvolver Métodos
	
	@Override
	public String mainPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toHTML() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getExtId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Teens(String login, String senha, String nome, UserType tipo, int externalID, GA grupo,
			LinkedList<Relatorio> relatorios) {
		super(login, senha, nome, tipo, externalID);
		this.grupo = grupo;
		this.relatorios = relatorios;
	}
	

}
