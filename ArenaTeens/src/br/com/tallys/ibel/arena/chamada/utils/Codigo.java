package br.com.tallys.ibel.arena.chamada.utils;

import br.com.tallys.ibel.arena.chamada.model.User;

public class Codigo {
	private int id;
	private String cod;
	private CodigoType tipo;
	private int extId;
	
	
	public Codigo(int id, CodigoType tipo, int extId) {
		super();
		this.id = id;
		this.cod = this.idToCod(id);
		this.tipo = tipo;
		this.extId = extId;
	}
	
	
	
	public Codigo(int id, String cod, CodigoType tipo, int extId) {
		super();
		this.id = id;
		this.cod = cod;
		this.tipo = tipo;
		this.extId = extId;
	}



	public String idToCod(int id) {
		String key = User.encrypt("H", "T");
		String base = new Integer(id).toString();
		String cod = User.encrypt(base, key);
		String result = "";
		for(int i = 0; i < cod.length(); i++) {
			if(!(cod.charAt(i) >= '0' && cod.charAt(i) <= '9')) {
				result += cod.charAt(i);
			}
		}
		return result;
	}



	public String getCod() {
		return cod;
	}



	public CodigoType getTipo() {
		return tipo;
	}



	public int getExtId() {
		return extId;
	}
	
	
	
	
}
