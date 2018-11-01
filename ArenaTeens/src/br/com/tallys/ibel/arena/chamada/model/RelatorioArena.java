package br.com.tallys.ibel.arena.chamada.model;

public class RelatorioArena {

	private String data;
	private int teen;
	private boolean presenca;
	private boolean pontualidade;
	private boolean meditacao;
	private boolean anotSexta;
	private boolean anotDomingo;
	private int versiculos;
	private int extras;
	private int total;
	private boolean fechamentoBim;
	private boolean fechamentoAnual;
	
	public String toHTML() {
		return null;		
	}

	public RelatorioArena(String data, int teen, boolean presenca, boolean pontualidade, boolean meditacao,
			boolean anotSexta, boolean anotDomingo, int versiculos, int extras, boolean fechamentoBim, boolean fechamentoAnual) {
		super();
		this.total = 0;
		this.data = data;
		this.teen = teen;
		this.presenca = presenca; 			this.total += presenca?		Constantes.PRESENCA_ARENA		:0;
		this.pontualidade = pontualidade;	this.total += pontualidade?	Constantes.PONTUALIDADE_ARENA	:0;
		this.meditacao = meditacao;			this.total += meditacao?	Constantes.MEDITACAO			:0;
		this.anotSexta = anotSexta;			this.total += anotSexta?	Constantes.ANOTACAO_SEXTA		:0;
		this.anotDomingo = anotDomingo;		this.total += anotDomingo?	Constantes.ANOTACAO_DOMINGO		:0;
		this.versiculos = versiculos;		this.total += meditacao?	versiculos*Constantes.CADA_VERSICULO:0;
		this.extras = extras;				this.total += extras;	
		this.fechamentoAnual = fechamentoAnual;
		this.fechamentoBim = fechamentoBim;
		
	}
	
	
	
}
