package br.com.tallys.ibel.arena.chamada.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import br.com.tallys.ibel.arena.chamada.jdbc.Factory;
import br.com.tallys.ibel.arena.chamada.model.Arena;
import br.com.tallys.ibel.arena.chamada.model.GA;
import br.com.tallys.ibel.arena.chamada.model.Lider;
import br.com.tallys.ibel.arena.chamada.model.Relatorio;
import br.com.tallys.ibel.arena.chamada.model.Sublider;
import br.com.tallys.ibel.arena.chamada.model.Teens;
import br.com.tallys.ibel.arena.chamada.model.User;
import br.com.tallys.ibel.arena.chamada.model.Enum.UserType;

public class UserDao {

	
	public static User login(String login, String senha) throws SQLException, ClassNotFoundException, IOException {
		Connection connect = null;
		connect = new Factory().getConnection();
		String encripted =  User.encrypt(login, senha);
		
		String consult = "SELECT * FROM usuario WHERE usu_login=\'"+login+"\' AND usu_password=\'"+encripted+"\'";
		
		ResultSet resultSet = null;
		Statement statement = connect.createStatement();
		resultSet = statement.executeQuery(consult);

		
		if(!resultSet.next()) {
			System.out.println("nenhum Usuario Encontrado com " + login + " / " + senha + " / " + encripted);
			return null;
		}
	
		int id = resultSet.getInt(1);
		String nome = resultSet.getString(2);
		UserType tipo;
		
		int c = resultSet.getInt(5);
		switch(c) {
		case 0:			tipo = UserType.Lider;		break;
		case 1:			tipo = UserType.Sublider; 	break;
		case 2:			tipo = UserType.Teen; 		break;
		default: ; return null;
		}
		
		int extId = resultSet.getInt(6);
		
		Arena ar;
		GA ga;
		switch(tipo) {
		case Lider:
			ar = new ArenaDao().getArena();
			return new Lider(login, senha, nome, extId, ar);
		case Sublider:
			ga = new GADao().getGA(extId);
			return new Sublider(login, senha, nome, extId, ga);
		case Teen:
			ga = new GADao().getGA(extId);
			LinkedList<Relatorio> rel = new RelatorioDao().getRelatorios(id);
			return new Teens(login, senha, nome, extId, ga, rel);
		}
		
		
		
		return null;
	}

	//Recupera todos os usuarios de tipo, porem não instancia arena e ga
	public static LinkedList<User> getAllType(UserType tipo, int extId) throws SQLException, ClassNotFoundException, IOException {
		Connection connect = null;
		connect = new Factory().getConnection();
		String consult = null;
		if(tipo.equals(UserType.Lider) || extId == -1) {
			consult = "SELECT * FROM usuario WHERE usu_type=\'"+tipo.ordinal()+"\'";
		}else {
			consult = "SELECT * FROM usuario WHERE usu_type=\'"+tipo.ordinal()+"\' AND usu_ref=\'"+extId+"\'";
		}
		
		
		ResultSet resultSet = null;
		
		Statement statement = connect.createStatement();
		resultSet = statement.executeQuery(consult);
		
		LinkedList<User> result = new LinkedList<User>();
		while(resultSet.next()) {
			
			int id = resultSet.getInt(1);
			String nome = resultSet.getString(2);
			String login = resultSet.getString(3);
			String senha = null;
			extId = resultSet.getInt(6);
			
			switch(tipo) {
			case Lider:
				result.add(new Lider(login, senha, nome, extId, null));
				break;
			case Sublider:
				result.add(new Sublider(login, senha, nome, extId, null));
				break;
			case Teen:
				LinkedList<Relatorio> rel = new RelatorioDao().getRelatorios(id);
				result.add(new Teens(login, senha, nome, extId, null, rel));
				break;
			}
			
			
		}
		return result;
	}
	
	public static boolean isUsed(String login) throws ClassNotFoundException, IOException, SQLException {
		Connection connect = null;
		connect = new Factory().getConnection();		
		String consult = "SELECT * FROM usuario WHERE usu_login=\'"+login+"\'";
		ResultSet resultSet = null;
		Statement statement = connect.createStatement();
		resultSet = statement.executeQuery(consult);
		boolean result = resultSet.next();
		connect.close();
		return result;
	}
	
	public static void Cadastrar(String login, String senha, int tipo, String nome, int extId) throws ClassNotFoundException, IOException, SQLException {
		Connection connect = null;
		connect = new Factory().getConnection();			
		String consult = "INSERT INTO `usuario`(`usu_name`, `usu_login`, `usu_password`, `usu_type`, `usu_ref`) VALUES (?,?,?,?,?)";
		PreparedStatement pstmt = connect.prepareStatement(consult);
        pstmt.setString(1, nome);
        pstmt.setString(2, login);
        pstmt.setString(3, senha);
        pstmt.setInt(4, tipo);
        pstmt.setInt(5, extId);
		pstmt.executeUpdate();
		
		
		if(tipo==0) {
			connect.close();
			return;
		}
		consult = "SELECT * FROM `usuario` WHERE usu_login=\'"+login+"\'";
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);
		int id = resultSet.getInt(0);
		if(tipo==1) consult = "INSERT INTO `usu_sublider`(`subl_id`, `subl_codga`) VALUES (?,?)";
		if(tipo==2) consult = "INSERT INTO `usu_teen`(`teen_id`, `teen_codga`) VALUES (?,?)";
		
		pstmt = connect.prepareStatement(consult);
		pstmt.setInt(1, id);
		pstmt.setInt(2, extId);
		pstmt.executeUpdate();
		
		connect.close();
		return;	
	}
	
	
}
