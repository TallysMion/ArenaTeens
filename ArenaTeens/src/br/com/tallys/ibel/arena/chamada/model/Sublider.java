package br.com.tallys.ibel.arena.chamada.model;

import java.util.LinkedList;

import br.com.tallys.ibel.arena.chamada.model.Enum.UserType;

public class Sublider extends User {

	private GA grupo;
	
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

	public Sublider(String login, String senha, String nome, int externalID, GA grupo) {
		super(login, senha, nome, UserType.Sublider, externalID);
		this.grupo = grupo;
	}

	public void setGA(GA ga) {
		this.grupo = ga;		
	}


}
