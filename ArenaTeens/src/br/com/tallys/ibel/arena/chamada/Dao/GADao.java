package br.com.tallys.ibel.arena.chamada.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import br.com.tallys.ibel.arena.chamada.jdbc.Factory;
import br.com.tallys.ibel.arena.chamada.model.Fechamento;
import br.com.tallys.ibel.arena.chamada.model.GA;
import br.com.tallys.ibel.arena.chamada.model.Sublider;
import br.com.tallys.ibel.arena.chamada.model.Teens;
import br.com.tallys.ibel.arena.chamada.model.User;
import br.com.tallys.ibel.arena.chamada.model.Enum.UserType;
import br.com.tallys.ibel.arena.chamada.utils.Codigo;
import br.com.tallys.ibel.arena.chamada.utils.CodigoType;

public class GADao {

	public static LinkedList<GA> getAll() throws ClassNotFoundException, IOException, SQLException {
		
		
		Connection connect = new Factory().getConnection();		
		String consult = "SELECT * FROM ga";		
		
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);
		
		LinkedList<GA> result = new LinkedList<GA>();
		
		while(resultSet.next()) {
			LinkedList<User> aux;
			LinkedList<Sublider> sublds = new LinkedList<Sublider>();
			LinkedList<Teens> teens = new LinkedList<Teens>();
			LinkedList<Fechamento> relatorios;
			
			GA ga = new GA();
			int id = resultSet.getInt(1);
			String nome = resultSet.getString(2);

			aux = new UserDao().getAllType(UserType.Sublider, id);
			for(int i=0; i < aux.size(); i++) {
				Sublider t = (Sublider) aux.get(i);
				t.setGA(ga);
				sublds.add(t);
			}
			
			aux = new UserDao().getAllType(UserType.Teen, id);
			for(int i=0; i < aux.size(); i++) {
				Teens t = (Teens) aux.get(i);
				t.setGA(ga);
				teens.add(t);
			}
			
			
			
			ga.update(nome, sublds, teens);
			result.add(ga);
			
		}		
		return result;
	}
	
	public static GA getGA(int extId) throws ClassNotFoundException, IOException, SQLException {
		Connection connect = new Factory().getConnection();		
		String consult = "SELECT * FROM ga WHERE ga_id=\'"+extId+"\'";			
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);
		
		GA result = new GA();
		int id;
		if(resultSet != null && resultSet.next()) {
			id = resultSet.getInt(1);
		}else {
			System.out.println("Ga não encontrado [" + extId + "]");
			return null;
		}
		String nome = resultSet.getString(2);
		
		LinkedList<User> aux;
		LinkedList<Sublider> sublds = new LinkedList<Sublider>();
		LinkedList<Teens> teens = new LinkedList<Teens>();

		aux = new UserDao().getAllType(UserType.Sublider, id);
		for(int i=0; i < aux.size(); i++) {
			Sublider t = (Sublider) aux.get(i);
			t.setGA(result);
			sublds.add(t);
		}
		
		aux = new UserDao().getAllType(UserType.Teen, id);
		for(int i=0; i < aux.size(); i++) {
			Teens t = (Teens) aux.get(i);
			t.setGA(result);
			teens.add(t);
		}
		
		result.update(nome, sublds, teens);
		
		return result;
	}
	
	public static boolean isUsed(String nome) throws ClassNotFoundException, IOException, SQLException {
		Connection connect = null;
		connect = new Factory().getConnection();		
		String consult = "SELECT * FROM `ga` WHERE ga_nome=\'"+nome+"\'";
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);
		boolean result = resultSet.next();
		connect.close();
		return result;
	}
	
	public static void Cadastrar(GA ga) throws ClassNotFoundException, IOException, SQLException {
		Connection connect = null;
		connect = new Factory().getConnection();			
		
		String consult = "INSERT INTO `ga`(`ga_nome`) VALUES (?)";
		PreparedStatement pstmt = connect.prepareStatement(consult);
        pstmt.setString(1, ga.getNome());
		pstmt.executeUpdate();
		
		connect.close();

	}
	
	public static String codGATeen(GA ga) throws ClassNotFoundException, IOException, SQLException {
		Connection connect = null;
		connect = new Factory().getConnection();			
		String consult = "SELECT * FROM `ga` WHERE ga_nome=\'"+ ga.getNome() +"\'";
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);
		int id;
		if(resultSet != null && resultSet.next()) {
			id = resultSet.getInt(1);
		}else {
			return null;
		}
		
		Codigo cd = new Codigo(0, CodigoType.Teen, id);
		
		CodigoDao.insert(cd);		
		
		return cd.getCod();		
	}
	
	public static String codGASublider(GA ga) throws ClassNotFoundException, IOException, SQLException {
		Connection connect = null;
		connect = new Factory().getConnection();			
		String consult = "SELECT * FROM `ga` WHERE ga_nome=\'"+ ga.getNome() +"\'";
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);
		int id;
		if(resultSet != null && resultSet.next()) {
			id = resultSet.getInt(1);
		}else {
			return "Error";
		}
		
		Codigo cd = new Codigo(0, CodigoType.Sublider, id);
		
		CodigoDao.insert(cd);		
		
		return cd.getCod();		
	}

	public static int getId(GA ga) throws ClassNotFoundException, IOException, SQLException {
		Connection connect = null;
		connect = new Factory().getConnection();		
		String consult = "SELECT * FROM `ga` WHERE ga_nome=\'"+ga.getNome()+"\'";
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);
		int result;
		if(resultSet != null && resultSet.next()) {
			result = resultSet.getInt(1);
		}else {
			return -1;
		}
		connect.close();
		return result;
	}
	
	
	

}
