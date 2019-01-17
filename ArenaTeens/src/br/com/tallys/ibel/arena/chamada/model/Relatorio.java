package br.com.tallys.ibel.arena.chamada.model;

import java.util.Date;

import br.com.tallys.ibel.arena.chamada.model.Enum.relatorioType;

public abstract class Relatorio {
	private int id;
	protected Date data;
	protected relatorioType tipo;
	protected Teens pessoa;
	protected int pontuacao;
	
	public boolean closedBim;
	protected Fechamento fechamentoBim;
	public boolean closed;
	protected Fechamento fechamento;
	

	
	public Relatorio(relatorioType tipo, Teens pessoa, int pontuacao, Fechamento fechamento, Fechamento fechamentoBim, Date data) {
		super();
		this.id =-1;
		this.data = data;
		this.tipo = tipo;
		this.pessoa = pessoa;
		this.pontuacao = pontuacao;
		this.closedBim = fechamentoBim==null?false:true;;
		this.fechamentoBim = fechamentoBim;		
		this.closed = fechamento==null?false:true;;
		this.fechamento = fechamento;
	}
	
	public Relatorio(int id, relatorioType tipo, Teens pessoa, int pontuacao, Fechamento fechamento, Fechamento fechamentoBim, Date data) {
		super();
		this.id = id;
		this.data = data;
		this.tipo = tipo;
		this.pessoa = pessoa;
		this.pontuacao = pontuacao;
		this.closedBim = fechamentoBim==null?false:true;;
		this.fechamentoBim = fechamentoBim;		
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
	
	
	public Date getData() {
		return data;
	}

	public Fechamento getFechamentoBim() {
		return fechamentoBim;
	}


	public Fechamento getFechamento() {
		return fechamento;
	}

	public abstract String toString();
	
}
