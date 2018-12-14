package br.com.tallys.ibel.arena.chamada.model;

import java.util.Date;
import java.util.LinkedList;

import br.com.tallys.ibel.arena.chamada.model.Enum.relatorioType;

public class Fechamento {
	
	private Date inicio;
	private Date fim;
	private int tipo;
	private LinkedList<Relatorio> relatorios;
	
	public void update(Date inic, Date end, int tipo2, LinkedList<Relatorio> relatorios2) {
		this.inicio = inic;
		this.fim = end;
		this.tipo = tipo2;
		this.relatorios = relatorios2;
	}
}
