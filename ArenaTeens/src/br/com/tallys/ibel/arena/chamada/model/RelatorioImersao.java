package br.com.tallys.ibel.arena.chamada.model;

import java.util.Date;

import br.com.tallys.ibel.arena.chamada.model.Enum.relatorioType;

public class RelatorioImersao extends Relatorio {
	
	private boolean presenca;
	private int pontosExtras;
	
	private static final int ptsPresenca = 2;
	
	public RelatorioImersao(Date data, Fechamento fechamento,Fechamento fechamentoBim,  Teens pessoa, boolean presenca, int pontosExtras) {
		super(relatorioType.relatorioImersao, pessoa, 0, fechamento, fechamentoBim, data);
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


	@Override
	public String toString() {
		String result = "<li class=\"list-group-item\">" 
	+ this.data.getDate() +"/"+ (this.data.getMonth()+1) +"/"+ (this.data.getYear()+1900) + " - Pontuacao Total: " + this.pontuacao + "<br>"
				+ "Presenca: " + (this.presenca?"V":"F") 
				+ "  	Extra: " + (this.pontosExtras)				
				+ "</li>\r\n";
		return result;
	}
}
