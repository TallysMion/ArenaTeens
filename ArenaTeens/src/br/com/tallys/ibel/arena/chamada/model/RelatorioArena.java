package br.com.tallys.ibel.arena.chamada.model;

import java.util.Date;

import br.com.tallys.ibel.arena.chamada.model.Enum.relatorioType;

public class RelatorioArena extends Relatorio {
	private boolean presenca; // 0 pts
	private boolean meditacao; // 5 pts
	private boolean pontualidade; // 2 pts
	private boolean anotDomingo; // 5 pts
	private boolean anotArena; // 5 pts
	private int versiculos; // 2 pts cada
	private int pontosExtras; // definido pelo lider
	
	private static final int ptsPresenca = 0;
	private static final int ptsMeditacao = 5;
	private static final int ptsPontualidade = 2;
	private static final int ptsAnotDomingo = 5;
	private static final int ptsAnotArena = 5;
	private static final int ptsVersiculo = 2;
	
	public RelatorioArena (Date data, Fechamento fechamento,Fechamento fechamentoBim, Teens pessoa, boolean presenca, boolean meditacao,
			boolean pontualidade, boolean anotDomingo, boolean anotArena, int versiculos, int pontosExtras) {
		super(relatorioType.relatorioArena, pessoa, 0, fechamento, fechamentoBim, data);
		int pont = 0;
		this.presenca = presenca; 
		pont += presenca ? ptsPresenca:0;
		this.meditacao = meditacao;
		pont += meditacao ? ptsMeditacao:0;
		this.pontualidade = pontualidade;
		pont += pontualidade ? ptsPontualidade:0;
		this.anotDomingo = anotDomingo;
		pont += anotDomingo ? ptsAnotDomingo:0;
		this.anotArena = anotArena;
		pont += anotArena ? ptsAnotArena:0;
		this.versiculos = versiculos;
		pont += (versiculos * ptsVersiculo);
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
				+ this.pessoa.nome +" - "+ + this.data.getDate() +"/"+ (this.data.getMonth()+1) +"/"+ (this.data.getYear()+1900) + "<br>Pontuacao Total: " + this.pontuacao + "<br>"
				+ "<div class=\"col-6\">Presenca: " + (this.presenca?"V":"F") + "</div>" 
				+ "<div class=\"col-6\">Meditacao: " + (this.meditacao?"V":"F") + "</div>"  
				+ "<div class=\"col-6\">Pontualidade: " + (this.pontualidade?"V":"F") + "</div>"
				+ "<div class=\"col-12\"> Anotacao de Domingo: " + (this.anotDomingo?"V":"F") + "</div>"
				+ "<div class=\"col-12\"> Anotacao do Arena: " + (this.anotArena?"V":"F") + "</div>"
				+ "<div class=\"col-6\"> Versiculos: " + (this.versiculos) + "</div>"
				+ "<div class=\"col-6\"> Extra: " + (this.pontosExtras)+ "</div>"
				+ "</li>\r\n";
		return result;
	}

	public boolean isPresenca() {
		return presenca;
	}

	public boolean isMeditacao() {
		return meditacao;
	}

	public boolean isPontualidade() {
		return pontualidade;
	}

	public boolean isAnotDomingo() {
		return anotDomingo;
	}

	public boolean isAnotArena() {
		return anotArena;
	}

	public int getVersiculos() {
		return versiculos;
	}

	public int getPontosExtras() {
		return pontosExtras;
	}
	
}
