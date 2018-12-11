package br.com.tallys.ibel.arena.chamada.model;

import br.com.tallys.ibel.arena.chamada.model.Enum.relatorioType;

public class RelatorioImersao extends Relatorio {
	
	private boolean presenca;
	private int pontosExtras;
	
	private static final int ptsPresenca = 5;
	
	public RelatorioImersao(Fechamento fechamento, Teens pessoa, boolean presenca, int pontosExtras) {
		super(relatorioType.relatorioImersao, pessoa, 0, fechamento);
		int pont = 0;
		this.presenca = presenca;
		pont += presenca ? ptsPresenca:0;
		this.pontosExtras = pontosExtras;
		pont += pontosExtras;
		this.setPontuacao(pont);
	}
	
	
	@Override
	public String toHTML() {
		// TODO Auto-generated method stub
		return null;
	}
}
