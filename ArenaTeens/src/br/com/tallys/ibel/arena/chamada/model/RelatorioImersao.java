package br.com.tallys.ibel.arena.chamada.model;

public class RelatorioImersao {

	private int teen;
	private String data;
	private boolean presenca;
	private int total;
	

	
	public RelatorioImersao(int teen,String data, boolean presenca) {
		super();
		this.teen = teen;
		this.data = data;
		this.presenca = presenca;
		this.total = presenca?Constantes.PRESENCA_IMERSAO:0;
	}



	public String toHTML() {
		return null;		
	}
	
	
	
}
