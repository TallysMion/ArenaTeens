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
	+ this.data.getDate() +"/"+ (this.data.getMonth()+1) +"/"+ (this.data.getYear()+1900) + " - Pontuacao Total: " + this.pontuacao + "<br>"
				+ "Presenca: " + (this.presenca?"V":"F") 
				+ "  	Meditacao: " + (this.meditacao?"V":"F")  
				+ "  	Pontualidade: " + (this.pontualidade?"V":"F")
				+ "<br>"
				+ "Anotacao de Domingo: " + (this.anotDomingo?"V":"F")
				+ "  	Anotacao do Arena: " + (this.anotArena?"V":"F")
				+ "<br>"
				+ "Versiculos: " + (this.versiculos)
				+ "  	Extra: " + (this.pontosExtras)				
				+ "</li>\r\n";
		return result;
	}
	
}
