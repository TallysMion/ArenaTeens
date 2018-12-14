package br.com.tallys.ibel.arena.chamada.model;

import br.com.tallys.ibel.arena.chamada.model.Enum.UserType;

public abstract class User {
	
	private String login;
	private String senha;
	protected String nome;
	private UserType tipo;
	private int externalID;
	
	
	
	public User(String login, String senha, String nome, UserType tipo, int externalID) {
		super();
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.tipo = tipo;
		this.externalID = externalID;
	}
	
	

	public User() {
		super();
	}
	
	//Metodo de criptografia da senha
	public static String encrypt(String user, String password) {
	      String sign = user + password;
	      try {
	         java.security.MessageDigest md =
	            java.security.MessageDigest.getInstance("MD5");
	         md.update(sign.getBytes());
	         byte[] hash = md.digest();
	         StringBuffer hexString = new StringBuffer();
	         for (int i = 0; i < hash.length; i++) {
	            if ((0xff & hash[i]) < 0x10)
	               hexString.append(
	                  "0" + Integer.toHexString((0xFF & hash[i])));
	            else
	               hexString.append(Integer.toHexString(0xFF & hash[i]));
	         }
	         sign = hexString.toString();
	      }
	      catch (Exception nsae) {
	         nsae.printStackTrace();
	      }
	      return sign;  
	}
	
	//Método de logout - o que usa?
	public void logout() {
		
	}
	
	//altera o nome do usuario
	public boolean changeUsername(String newName) {
		return false;
	}
	
	//Altera o password do usuario
	public boolean changePassword(String newPassword) {
		return false;
	}
	
	//Recupera o id do usuario
	public abstract int getExtId();
	
	//Retorna a pagina do usuario
	public abstract String mainPage();
	
	//Retorna um HTML de referencia ao usuario
	public abstract String toHTML();



	public abstract Object getType();

	

}
