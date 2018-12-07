package br.com.tallys.ibel.arena.chamada.model;

import br.com.tallys.ibel.arena.chamada.model.Enum.UserType;

public abstract class User {
	
	private String login;
	private String senha;
	private String nome;
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
	
	public static User Cadastrar(String login, String senha, String nome, UserType tipo, int externalID) {
		User result = null;
		switch(tipo){
		case Lider:
			//null -> arena
				result = new Lider(login, senha, nome, tipo, externalID, null);
		break;
		case Sublider:
			//null -> ga
				result = new Sublider(login, senha, nome, tipo, externalID, null);
		break;
		case Teen:
			//1° null -> ga
			//2° null -> relatorios
				result = new Teens(login, senha, nome, tipo, externalID, null, null);
		break;
		}
		//Desenvolver**
		//recuperar o id de result
		
		return result;
		
	}



	//Método de Login no Sistema
	public User Login(String login, String senha) {
		//encripta a senha
		//busca usuario e senha correspondente
		//verifica o tipo do usuario
		//recupera os dados do usuario
		//instancia o objeto
		//retorna o usuario		
		return null;
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
	

}
