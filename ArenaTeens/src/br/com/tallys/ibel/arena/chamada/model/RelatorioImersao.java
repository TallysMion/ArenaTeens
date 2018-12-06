package br.com.tallys.ibel.arena.chamada.model;

import br.com.tallys.ibel.arena.chamada.model.Enum.relatorioType;

public class RelatorioImersao extends Relatorio {
	
	public RelatorioImersao(relatorioType tipo, Teens pessoa, int pontuacao) {
		super(tipo, pessoa, pontuacao);
		// TODO Auto-generated constructor stub
	}
	
	private boolean presenca;
	private int pontosExtras;
}
