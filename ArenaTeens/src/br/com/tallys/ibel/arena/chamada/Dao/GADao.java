package br.com.tallys.ibel.arena.chamada.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import br.com.tallys.ibel.arena.chamada.jdbc.Factory;
import br.com.tallys.ibel.arena.chamada.model.GA;
import br.com.tallys.ibel.arena.chamada.model.Sublider;
import br.com.tallys.ibel.arena.chamada.model.Teens;
import br.com.tallys.ibel.arena.chamada.model.User;
import br.com.tallys.ibel.arena.chamada.model.Enum.UserType;

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
		int id = resultSet.getInt(1);
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
	
	

}
