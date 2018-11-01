package br.com.tallys.ibel.arena.chamada.model;

import br.com.tallys.ibel.arena.chamada.model.Enum.relatorioType;

public class RelatorioArena extends Relatorio {
	private boolean presenca; // 5 pts
	private boolean meditacao; // 5 pts
	private boolean pontualidade; // 5 pts
	private boolean anotDomingo; // 5 pts
	private boolean anotArena; // 5 pts
	private int versiculos; // 2 pts cada
	private int pontosExtras; // definido pelo lider
	
	private static final int ptsPresenca = 5;
	private static final int ptsMeditacao = 5;
	private static final int ptsPontualidade = 5;
	private static final int ptsAnotDomingo = 5;
	private static final int ptsAnotArena = 5;
	private static final int ptsVersiculo = 2;
	
	public RelatorioArena (Teens pessoa, int pontuacao, boolean presenca, boolean meditacao,
			boolean pontualidade, boolean anotDomingo, boolean anotArena, int versiculos, int pontosExtras) {
		super(relatorioType.relatorioArena, pessoa, 0);
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
	
}
