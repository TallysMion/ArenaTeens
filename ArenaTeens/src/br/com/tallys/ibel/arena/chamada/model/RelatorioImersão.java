package br.com.tallys.ibel.arena.chamada.model;

public class RelatorioImers�o {

	private int id;
	private int teen;
	private boolean presenca;
	private int total;
	

	
	public RelatorioImers�o(int id, int teen, boolean presenca) {
		super();
		this.id = id;
		this.teen = teen;
		this.presenca = presenca;
		this.total = presenca?Constantes.PRESENCA_IMERSAO:0;
	}



	public String toHTML() {
		return null;		
	}
	
	
	
}
