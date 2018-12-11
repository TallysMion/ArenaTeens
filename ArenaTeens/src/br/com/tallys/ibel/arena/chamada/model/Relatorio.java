package br.com.tallys.ibel.arena.chamada.model;

import br.com.tallys.ibel.arena.chamada.model.Enum.relatorioType;

public abstract class Relatorio {
	private relatorioType tipo;
	private Teens pessoa;
	private int pontuacao;
	
	private boolean closed;
	private Fechamento fechamento;
	

	
	public Relatorio(relatorioType tipo, Teens pessoa, int pontuacao, Fechamento fechamento) {
		super();
		this.tipo = tipo;
		this.pessoa = pessoa;
		this.pontuacao = pontuacao;
		this.closed = fechamento==null?false:true;;
		this.fechamento = fechamento;
	}

	public abstract String toHTML();

	public int getPontuacao() {
		return pontuacao;
	}
	
	protected void setPontuacao(int pont) {
		this.pontuacao = pont;
	}
	public relatorioType getTipo() {
		return tipo;
	}
	
	public Teens getPessoa() {
		return pessoa;
	}

	public void setFechamento(Fechamento fechamento) {
		this.fechamento = fechamento;
	}
}
