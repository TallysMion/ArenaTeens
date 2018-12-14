package br.com.tallys.ibel.arena.chamada.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import br.com.tallys.ibel.arena.chamada.Dao.CodigoDao;
import br.com.tallys.ibel.arena.chamada.Dao.GADao;

public class GA {
	
	private String nome; 
	private LinkedList<Sublider> sublideres;
	private LinkedList<Teens> teens;
	private String codigoTeen;
	private String codigoSublider;
	
	public void update(String nome, LinkedList<Sublider> sublds, LinkedList<Teens> teens) {
		this.nome = nome;
		this.sublideres = sublds;
		this.teens = teens;	
		this.codigoTeen = "";
		this.codigoSublider = "";
	}
	
	public GA(String nome) {
		this.nome = nome;
		this.sublideres = new LinkedList<Sublider>();
		this.teens = new LinkedList<Teens>();
		this.codigoTeen = "";
		this.codigoSublider = "";
	}

	public GA() {
		this.nome = "";
		this.sublideres = new LinkedList<Sublider>();
		this.teens = new LinkedList<Teens>();
		this.codigoTeen = "";
		this.codigoSublider = "";
	}

	public String getNome() {
		return nome;
	}
	
	public String getCod() {
		return "Achar Codigo";
	}

	public LinkedList<Sublider> getSublideres() {
		return sublideres;
	}

	public LinkedList<Teens> getTeens() {
		return teens;
	}

	@Override
	public String toString() {
		return nome + "[" + "Cod Error" + "]";
	}
	
	public String toString(boolean ctrl) throws ClassNotFoundException, IOException, SQLException {
		
		int id = GADao.getId(this);
		if(ctrl) {
			if(this.codigoSublider.equals("")) {
				this.codigoSublider = CodigoDao.getCodOfGASublider(id).getCod();		
			}
			return nome + "[" + this.codigoSublider + "]";
		}else {
			if(this.codigoTeen.equals("")) {
				this.codigoTeen = CodigoDao.getCodOfGATeen(id).getCod();
			}
			return nome + "[" + this.codigoTeen + "]";
		}
	}
	
	public String toTitle(boolean ctrl) throws ClassNotFoundException, IOException, SQLException {
		int id = GADao.getId(this);
		if(ctrl) {
			if(this.codigoSublider.equals("")) {
				this.codigoSublider = CodigoDao.getCodOfGASublider(id).getCod();
				return nome + "[" + this.codigoSublider + "]";
			}
		}else {
			if(this.codigoTeen.equals("")) {
				this.codigoTeen = CodigoDao.getCodOfGATeen(id).getCod();
				return nome + "[" + this.codigoTeen + "]";
			}
		}
		return nome + "[" + "Cod Error" + "]";
	}

	public String getCodigoTeen() {
		return codigoTeen;
	}

	public void setCodigoTeen(String codigoTeen) {
		this.codigoTeen = codigoTeen;
	}

	public String getCodigoSublider() {
		return codigoSublider;
	}

	public void setCodigoSublider(String codigoSublider) {
		this.codigoSublider = codigoSublider;
	}

	
	
	
	
	
}
