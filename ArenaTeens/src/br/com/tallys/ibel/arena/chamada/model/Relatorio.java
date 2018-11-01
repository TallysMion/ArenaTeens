package br.com.tallys.ibel.arena.chamada.model;

import br.com.tallys.ibel.arena.chamada.model.Enum.relatorioType;

public abstract class Relatorio {
	private relatorioType tipo;
	private Teens pessoa;
	private int pontuacao;

	public Relatorio (relatorioType tipo, Teens pessoa, int pontuacao) {
		super();
		this.tipo = tipo;
		this.pessoa = pessoa;
		this.pontuacao = pontuacao;
	}
	
	public String toHTML () {
		//DESENVOLVER
	}

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
}
