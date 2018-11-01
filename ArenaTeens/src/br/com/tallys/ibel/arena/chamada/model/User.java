package br.com.tallys.ibel.arena.chamada.model;

public abstract class User {
	
	private String login;
	private String senha;
	private UserType tipo;
	
	protected String emcript(String value) {
		value = value = value.replaceAll("a", "q");		
		value = value.replaceAll("b", "w");	
		value = value.replaceAll("c", "e");		
		value = value.replaceAll("d", "r");
		value = value.replaceAll("e", "t");		
		value = value.replaceAll("f", "y");		
		value = value.replaceAll("g", "u");		
		value = value.replaceAll("h", "i");
		value = value.replaceAll("i", "o");		
		value = value.replaceAll("j", "p");	
		value = value.replaceAll("k", "a");		
		value = value.replaceAll("l", "s");
		value = value.replaceAll("m", "d");		
		value = value.replaceAll("r", "k");		
		value = value.replaceAll("s", "l");		
		value = value.replaceAll("t", "ç");
		value = value.replaceAll("u", "z");		
		value = value.replaceAll("v", "x");		
		value = value.replaceAll("w", "c");	
		value = value.replaceAll("x", "v");
		value = value.replaceAll("y", "b");		
		value = value.replaceAll("z", "n");	
		value = value.replaceAll("A", "m");	
		value = value.replaceAll("B", ",");
		value = value.replaceAll("C", ".");		
		value = value.replaceAll("D", " ");		
		value = value.replaceAll("E", "Q");		
		value = value.replaceAll("F", "W");
		value = value.replaceAll("G", "E");		
		value = value.replaceAll("H", "R");	
		value = value.replaceAll("I", "T");	
		value = value.replaceAll("J", "Y");
		value = value.replaceAll("K", "U");		
		value = value.replaceAll("L", "I");	
		value = value.replaceAll("M", "O");		
		value = value.replaceAll("N", "P");
		value = value.replaceAll("O", "A");		
		value = value.replaceAll("P", "S");		
		value = value.replaceAll("Q", "D");		
		value = value.replaceAll("R", "F");
		value = value.replaceAll("S", "G");		
		value = value.replaceAll("T", "H");		
		value = value.replaceAll("U", "J");		
		value = value.replaceAll("V", "K");
		value = value.replaceAll("W", "L");		
		value = value.replaceAll("X", "Ç");		
		value = value.replaceAll("Y", "Z");		
		value = value.replaceAll("Z", "X");
		value = value.replaceAll(" ", "!");			
		value = value.replaceAll("2", "#");		
		value = value.replaceAll("3", "@");
		value = value.replaceAll("4", "%");		
		value = value.replaceAll("5", "&");	
		value = value.replaceAll("6", ")");		
		value = value.replaceAll("7", "(");
		value = value.replaceAll("8", "*");	
		value = value.replaceAll("9", "}");	
		value = value.replaceAll("!", "]");		
		value = value.replaceAll("@", "{");
		value = value.replaceAll("#", "[");	
		value = value.replaceAll("$", "<");
		value = value.replaceAll("%", ">");
		value = value.replaceAll("&", ";");
		value = value.replaceAll("-", "\\");
		value = value.replaceAll("_", "|");
		value = value.replaceAll("=", "RE");
		value = value.replaceAll("]", "6T");
		value = value.replaceAll("}", "3W");
		value = value.replaceAll("/", "2R");
		value = value.replaceAll(";", "WD");
		value = value.replaceAll(":", "NB");
		value = value.replaceAll("<", "DF");
		value = value.replaceAll(">", "SK");
		value = value.replaceAll("|", "WC");
		return value;
	}
	
	public abstract String constructHTML();
	
	public abstract String toHTML();
	
	
}
