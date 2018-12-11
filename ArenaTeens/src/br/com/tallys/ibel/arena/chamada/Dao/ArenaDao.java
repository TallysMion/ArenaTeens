package br.com.tallys.ibel.arena.chamada.Dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import br.com.tallys.ibel.arena.chamada.model.Arena;
import br.com.tallys.ibel.arena.chamada.model.GA;
import br.com.tallys.ibel.arena.chamada.model.Lider;
import br.com.tallys.ibel.arena.chamada.model.User;
import br.com.tallys.ibel.arena.chamada.model.Enum.UserType;

public class ArenaDao {

	
	public static Arena getArena() throws ClassNotFoundException, SQLException, IOException {
		
		LinkedList<User> aux;
		LinkedList<Lider> lds = new LinkedList<Lider>();
		
		LinkedList<GA> gas;
		aux = new UserDao().getAllType(UserType.Lider, -1);
		gas = new GADao().getAll();
		
		Arena result = new Arena();		

		for(int i=0; i < aux.size(); i++) {
			Lider t = (Lider) aux.get(i);
			t.setArena(result);
			lds.add(t);
		}	
		
		result.setLiders(lds);
		result.setGAs(gas);
		return result;
	}
	
	
}
