package br.com.tallys.ibel.arena.chamada.model;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import br.com.tallys.ibel.arena.chamada.model.Enum.FechamentoType;

public class Fechamento {
	private FechamentoType tipo;
	private GregorianCalendar inicio;
	private GregorianCalendar fim;
	private LinkedList<Teens> teens;
	
	public String toHTML() {
		return null;
		//Retorna o HTML do fechamento
	}
	
}
