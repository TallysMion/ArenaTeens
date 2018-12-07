package br.com.tallys.ibel.arena.chamada.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.tallys.ibel.arena.chamada.jdbc.Factory;
import br.com.tallys.ibel.arena.chamada.model.Lider;
import br.com.tallys.ibel.arena.chamada.model.Sublider;
import br.com.tallys.ibel.arena.chamada.model.Teens;
import br.com.tallys.ibel.arena.chamada.model.User;
import br.com.tallys.ibel.arena.chamada.model.Enum.UserType;

public class UserDao {

	
	public User login(String login, String senha) throws SQLException {
		Connection connect = null;
		try {
			connect = new Factory().getConnection();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String encripted =  User.encrypt(login, senha);
		
		String consult = "SELECT * FROM usuario WHERE usu_login=\'"+login+"\' AND usu_password=\'"+encripted+"\'";
		
		ResultSet resultSet = null;
		try{
			Statement statement = connect.createStatement();
			resultSet = statement.executeQuery(consult);
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	try {
		if(!resultSet.next()) {
			System.out.println("nenhum Usuario Encontrado com " + login + " / " + senha + " / " + encripted);
			return null;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		String nome = resultSet.getString(1);
		UserType tipo;
		
		int c = resultSet.getInt(5);
		switch(c) {
		case 0:			tipo = UserType.Lider;		break;
		case 1:			tipo = UserType.Sublider; 	break;
		case 2:			tipo = UserType.Teen; 		break;
		default: return null;
		}
		
		int extId = resultSet.getInt(6);
		
		switch(tipo) {
		case Lider:
			//null -> recuperar dados do Arena
			return new Lider(login, senha, nome,  tipo, extId, null);
		case Sublider:
			//null -> recuperar dados do GA
			return new Sublider(login, senha, nome, tipo, extId, null);
		case Teen:
			//1° null -> recuperar GA
			//2° null -> recuperar Relatorios
			return new Teens(login, senha, nome, tipo, extId, null, null);
		}
		
		
		
		return null;
	}
	
	
}
