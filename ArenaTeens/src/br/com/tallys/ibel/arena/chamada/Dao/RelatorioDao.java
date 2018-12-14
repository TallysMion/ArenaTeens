package br.com.tallys.ibel.arena.chamada.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;

import br.com.tallys.ibel.arena.chamada.jdbc.Factory;
import br.com.tallys.ibel.arena.chamada.model.Fechamento;
import br.com.tallys.ibel.arena.chamada.model.Relatorio;
import br.com.tallys.ibel.arena.chamada.model.RelatorioArena;
import br.com.tallys.ibel.arena.chamada.model.RelatorioImersao;

public class RelatorioDao {

	public static LinkedList<Relatorio> getRelatorios(int idTeen) throws SQLException, ClassNotFoundException, IOException {
		Connection connect = new Factory().getConnection();		
		
		String consult = "SELECT * FROM relatorios";			
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);

		LinkedList<Relatorio> result = new LinkedList<Relatorio>();
		try {
		while(resultSet.next()) {
			if(resultSet.getInt(5) != idTeen) continue;
			Relatorio it;
			
			int tipo = resultSet.getInt(2);
			int extId = resultSet.getInt(6);
			boolean fechB = resultSet.getBoolean(8);
			int idFechB = resultSet.getInt(9);
			boolean fech = resultSet.getBoolean(3);
			int idFech = resultSet.getInt(4);
			Date data = resultSet.getDate(10);
			
			Statement stat = connect.createStatement();
			ResultSet aux;
			
			Fechamento fechamentoB;
			if(fechB) {
				fechamentoB =new fechamentoDao().getFechamento(idFechB);
			}else {
				fechamentoB = null;
			}
			
			Fechamento fechamento;
			if(fech) {
				fechamento =new fechamentoDao().getFechamento(idFech);
			}else {
				fechamento = null;
			}
			
			switch(tipo) {
			case 0:
		
				consult = "SELECT * FROM rel_arena WHERE arena_rel_id=\'"+extId+"\'";			
				aux = stat.executeQuery(consult);		
				if(!aux.next()) return null;
				boolean presenca 	= aux.getBoolean(2);
				boolean meditacao 	= aux.getBoolean(3);
				boolean pontualidade= aux.getBoolean(4);
				boolean anotDomingo = aux.getBoolean(5);
				boolean anotArena	= aux.getBoolean(6);
				int versiculos		= aux.getInt(7);
				int pontosExtras	= aux.getInt(8);
				it = new RelatorioArena(data, fechamento, fechamentoB, null, presenca, meditacao,
						pontualidade, anotDomingo, anotArena, versiculos, pontosExtras);
				break;
			case 1:
				consult = "SELECT * FROM rel_imersao WHERE imer_rel_id=\'"+extId+"\'";			
				aux = stat.executeQuery(consult);
				if(!aux.next()) return null;
				boolean presenca2 	= aux.getBoolean(2);
				int pontosExtras2	= aux.getInt(3);
				it = new RelatorioImersao(data, fechamento, fechamentoB, null,	presenca2, pontosExtras2);
				break;
			default: return null;
			}
			result.add(it);					
		}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	//ta errado
	public static LinkedList<Relatorio> getRelatoriosFechamento(int idFech) throws SQLException, ClassNotFoundException, IOException {
		Connection connect = new Factory().getConnection();		
		
		String consult = "SELECT * FROM relatorios WHERE rel_idfech=\'"+idFech+"\'";			
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);

		LinkedList<Relatorio> result = new LinkedList<Relatorio>();
		
		Fechamento fechamento = null; //inserir no contrutor do fechamento
		Fechamento fechamentoB = null; //inserir no contrutor do fechamento
		
		while(resultSet.next()) {
			Relatorio it;			
			boolean fech = true;
			int tipo = resultSet.getInt(2);
			int extId = resultSet.getInt(6);
			Date data = resultSet.getDate(10);
			ResultSet aux;			
			switch(tipo) {
			case 0:
		
				consult = "SELECT * FROM rel_arena WHERE arena_rel_id=\'"+extId+"\'";			
				aux = statement.executeQuery(consult);		
				if(!aux.next()) return null;
				boolean presenca 	= aux.getBoolean(2);
				boolean meditacao 	= aux.getBoolean(3);
				boolean pontualidade= aux.getBoolean(4);
				boolean anotDomingo = aux.getBoolean(5);
				boolean anotArena	= aux.getBoolean(6);
				int versiculos		= aux.getInt(7);
				int pontosExtras	= aux.getInt(8);
				it = new RelatorioArena(data, fechamento, fechamentoB, null, presenca, meditacao,
						pontualidade, anotDomingo, anotArena, versiculos, pontosExtras);
				break;
			case 1:
				consult = "SELECT * FROM rel_imersao WHERE imer_rel_id=\'"+extId+"\'";			
				aux = statement.executeQuery(consult);
				if(!aux.next()) return null;
				boolean presenca2 	= aux.getBoolean(2);
				int pontosExtras2	= aux.getInt(3);
				it = new RelatorioImersao(data, fechamento, fechamentoB, null,	presenca2, pontosExtras2);
				break;
			default: return null;
			}
			result.add(it);					
		}
		return result;
	}

	
	//ta errado
	public LinkedList<Relatorio> getRelatoriosFechamentoBim(int idFech) throws ClassNotFoundException, IOException, SQLException {
Connection connect = new Factory().getConnection();		
		
		String consult = "SELECT * FROM relatorios WHERE rel_idfech=\'"+idFech+"\'";			
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);

		LinkedList<Relatorio> result = new LinkedList<Relatorio>();
		
		Fechamento fechamento = null; //inserir no contrutor do fechamento
		Fechamento fechamentoB = null; //inserir no contrutor do fechamento
		
		while(resultSet.next()) {
			Relatorio it;			
			boolean fech = true;
			int tipo = resultSet.getInt(2);
			int extId = resultSet.getInt(6);
			Date data = resultSet.getDate(10);
			ResultSet aux;			
			switch(tipo) {
			case 0:
		
				consult = "SELECT * FROM rel_arena WHERE arena_rel_id=\'"+extId+"\'";			
				aux = statement.executeQuery(consult);		
				
				boolean presenca 	= aux.getBoolean(2);
				boolean meditacao 	= aux.getBoolean(3);
				boolean pontualidade= aux.getBoolean(4);
				boolean anotDomingo = aux.getBoolean(5);
				boolean anotArena	= aux.getBoolean(6);
				int versiculos		= aux.getInt(7);
				int pontosExtras	= aux.getInt(8);
				it = new RelatorioArena(data, fechamento, fechamentoB, null, presenca, meditacao,
						pontualidade, anotDomingo, anotArena, versiculos, pontosExtras);
				break;
			case 1:
				consult = "SELECT * FROM rel_imersao WHERE imer_rel_id=\'"+extId+"\'";			
				aux = statement.executeQuery(consult);
				boolean presenca2 	= aux.getBoolean(2);
				int pontosExtras2	= aux.getInt(3);
				it = new RelatorioImersao(data, fechamento, fechamentoB, null,	presenca2, pontosExtras2);
				break;
			default: return null;
			}
			result.add(it);					
		}
		return result;
	}

}
