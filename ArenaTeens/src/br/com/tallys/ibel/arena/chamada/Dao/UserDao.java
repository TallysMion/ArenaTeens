package br.com.tallys.ibel.arena.chamada.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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
		String telefone = resultSet.getString(7);
		Date nasc = resultSet.getDate(8);
		
		Arena ar;
		GA ga;
		switch(tipo) {
		case Lider:
			ar = ArenaDao.getArena();
			connect.close();
			return new Lider(login, senha, nome, extId, telefone, nasc, ar);
		case Sublider:
			ga = GADao.getGA(extId);
			connect.close();
			return new Sublider(login, senha, nome, extId, telefone, nasc, ga);
		case Teen:
			ga = GADao.getGA(extId);
			LinkedList<Relatorio> rel = RelatorioDao.getRelatorios(id);
			connect.close();
			return new Teens(login, senha, nome, extId, telefone, nasc, ga, rel, id);
		}
		
		
		connect.close();
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
			String telefone = resultSet.getString(7);
			Date nasc = resultSet.getDate(8);
			
			switch(tipo) {
			case Lider:
				result.add(new Lider(login, senha, nome, extId, telefone, nasc, null));
				break;
			case Sublider:
				result.add(new Sublider(login, senha, nome, extId, telefone, nasc, null));
				break;
			case Teen:
				LinkedList<Relatorio> rel = RelatorioDao.getRelatorios(id);
				result.add(new Teens(login, senha, nome, extId, telefone, nasc, null, rel, id));
				break;
			}
			
			
		}
		connect.close();
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
	
	public static void Cadastrar(String login, String senha, int tipo, String nome, int extId, String telefone, Date nasc) throws ClassNotFoundException, IOException, SQLException {
		Connection connect = null;
		connect = new Factory().getConnection();			
		String consult = "INSERT INTO `usuario`(`usu_name`, `usu_login`, `usu_password`, `usu_type`, `usu_ref`, `usu_tel`, `usu_nasc`) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement pstmt = connect.prepareStatement(consult);
        pstmt.setString(1, nome);
        pstmt.setString(2, login);
        pstmt.setString(3, senha);
        pstmt.setInt(4, tipo);
        pstmt.setInt(5, extId);
        pstmt.setString(6, telefone);
        pstmt.setDate(7, new java.sql.Date(nasc.getTime()));
		pstmt.executeUpdate();
		
		
		if(tipo==0) {
			connect.close();
			return;
		}
		consult = "SELECT * FROM `usuario` WHERE usu_login=\'"+login+"\'";
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);
		int id;
		if(resultSet != null && resultSet.next()) {
			id = resultSet.getInt(1);
		}else {
			id = -1;
			return;
		}
		if(tipo==1) consult = "INSERT INTO `usu_sublider`(`subl_id`, `subl_codga`) VALUES (?,?)";
		if(tipo==2) consult = "INSERT INTO `usu_teen`(`teen_id`, `teen_codga`) VALUES (?,?)";
		
		pstmt = connect.prepareStatement(consult);
		pstmt.setInt(1, id);
		pstmt.setInt(2, extId);
		pstmt.executeUpdate();
		
		connect.close();
		return;	
	}

	public static void updateData(String login, String senha, String nlogin, Date nNasc, String nTel) throws ClassNotFoundException, IOException, SQLException {
		Connection connect = null;
		connect = new Factory().getConnection();			
		String consult = "UPDATE `usuario` SET `usu_name`=?,`usu_tel`=?,`usu_nasc`=? WHERE `usu_login`=? AND `usu_password`=?";
		PreparedStatement pstmt = connect.prepareStatement(consult);
        pstmt.setString(1, nlogin);
        pstmt.setString(2, nTel);
        pstmt.setDate(3, new java.sql.Date(nNasc.getTime()));
        pstmt.setString(4, login);
        pstmt.setString(5, User.encrypt(login, senha));
		pstmt.executeUpdate();  
		connect.close();
		return;
	}

	public static void updateKey(String login, String senha, String nSenha) throws ClassNotFoundException, IOException, SQLException {
		Connection connect = null;
		connect = new Factory().getConnection();			
		String consult = "UPDATE `usuario` SET `usu_password`=? WHERE `usu_login`=? AND `usu_password`=?";
		PreparedStatement pstmt = connect.prepareStatement(consult);
        pstmt.setString(1, nSenha);
        pstmt.setString(2, login);
        pstmt.setString(3, User.encrypt(login, senha));
		pstmt.executeUpdate();  
		connect.close();
		return;
	}
	
	public static User getUserById(int id) throws ClassNotFoundException, SQLException, IOException {
		Connection connect = null;
		connect = new Factory().getConnection();
		String consult = null;
		consult = "SELECT * FROM `usuario` WHERE `usu_id`="+id;
		
		
		ResultSet resultSet = null;
		
		Statement statement = connect.createStatement();
		resultSet = statement.executeQuery(consult);
			
		while(resultSet.next()) {
			
			String nome     = resultSet.getString(2);
			String login    = resultSet.getString(3);
			String senha    = resultSet.getString(4);
			int tp			= resultSet.getInt(5);
			int    extId    = resultSet.getInt(6);
			String telefone = resultSet.getString(7);
			Date   nasc     = resultSet.getDate(8);
			
			switch(tp) {
			case 0:
				connect.close();
				return (new Lider(login, senha, nome, extId, telefone, nasc, ArenaDao.getArena()));
			case 1:
				connect.close();
				return (new Sublider(login, senha, nome, extId, telefone, nasc, GADao.getGA(extId)));
			case 2:
				connect.close();
				return (new Teens(login, senha, nome, extId, telefone, nasc, GADao.getGA(extId), null, id));
			}
			
			
		}
		connect.close();
		return null;
	}
	
	
}
