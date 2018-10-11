package br.com.tallys.ibel.arena.chamada.model;

public abstract class User {
	
	private String login;
	private String senha;
	private UserType tipo;
	
	protected String emcript(String value) {
		value.replaceAll("a", "q");			value.replaceAll("b", "w");		value.replaceAll("c", "e");			value.replaceAll("d", "r");
		value.replaceAll("e", "t");			value.replaceAll("f", "y");		value.replaceAll("g", "u");			value.replaceAll("h", "i");
		value.replaceAll("i", "o");			value.replaceAll("j", "p");		value.replaceAll("k", "a");			value.replaceAll("l", "s");
		value.replaceAll("m", "d");			value.replaceAll("n", "f");		value.replaceAll("o", "g");			value.replaceAll("p", "h");
		value.replaceAll("q", "j");			value.replaceAll("r", "k");		value.replaceAll("s", "l");			value.replaceAll("t", "ç");
		value.replaceAll("u", "z");			value.replaceAll("v", "x");		value.replaceAll("w", "c");			value.replaceAll("x", "v");
		value.replaceAll("y", "b");			value.replaceAll("z", "n");		value.replaceAll("A", "m");			value.replaceAll("B", ",");
		value.replaceAll("C", ".");			value.replaceAll("D", " ");		value.replaceAll("E", "Q");			value.replaceAll("F", "W");
		value.replaceAll("G", "E");			value.replaceAll("H", "R");		value.replaceAll("I", "T");			value.replaceAll("J", "Y");
		value.replaceAll("K", "U");			value.replaceAll("L", "I");		value.replaceAll("M", "O");			value.replaceAll("N", "P");
		value.replaceAll("O", "A");			value.replaceAll("P", "S");		value.replaceAll("Q", "D");			value.replaceAll("R", "F");
		value.replaceAll("S", "G");			value.replaceAll("T", "H");		value.replaceAll("U", "J");			value.replaceAll("V", "K");
		value.replaceAll("W", "L");			value.replaceAll("X", "Ç");		value.replaceAll("Y", "Z");			value.replaceAll("Z", "X");
		value.replaceAll(" ", "!");			value.replaceAll("1", "$");		value.replaceAll("2", "#");			value.replaceAll("3", "@");
		value.replaceAll("4", "%");			value.replaceAll("5", "&");		value.replaceAll("6", ")");			value.replaceAll("7", "(");
		value.replaceAll("8", "*");			value.replaceAll("9", "}");		value.replaceAll("!", "]");			value.replaceAll("@", "{");
		value.replaceAll("#", "[");			value.replaceAll("$", "<");		value.replaceAll("%", ">");			value.replaceAll("&", ";");
		value.replaceAll("*", ":");			value.replaceAll("(", "?");		value.replaceAll(")", "/");			value.replaceAll("-", "\\");
		value.replaceAll("_", "|");			value.replaceAll("+", "QR");	value.replaceAll("=", "RE");		value.replaceAll("[", "DF");
		value.replaceAll("{", "FG");		value.replaceAll("]", "6T");	value.replaceAll("}", "3W");		value.replaceAll("/", "2R");
		value.replaceAll("?", "8U");		value.replaceAll(";", "WD");	value.replaceAll(":", "NB");		value.replaceAll("<", "DF");
		value.replaceAll(">", "SK");		value.replaceAll("\\", "GF");	value.replaceAll("|", "WC");		return value;
	}
	
	public abstract String constructHTML();
	
	public abstract String toHTML();
	
	
}
