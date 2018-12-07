package br.com.tallys.ibel.arena.chamada.model;

import br.com.tallys.ibel.arena.chamada.model.Enum.UserType;

public class Lider extends User {
	
	private Arena arena;
	
	//Verificar Métodos Nescessários

	@Override
	public String mainPage() {
		// TODO Auto-generated method stub
		return null;
	}

	public Lider(String login, String senha, String nome, UserType tipo, int externalID, Arena arena) {
		super(login, senha, nome, tipo, externalID);
		this.arena = arena;
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

}
