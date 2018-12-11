package br.com.tallys.ibel.arena.chamada.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import br.com.tallys.ibel.arena.chamada.jdbc.Factory;
import br.com.tallys.ibel.arena.chamada.model.Fechamento;
import br.com.tallys.ibel.arena.chamada.model.Relatorio;
import br.com.tallys.ibel.arena.chamada.model.RelatorioArena;
import br.com.tallys.ibel.arena.chamada.model.RelatorioImersao;

public class RelatorioDao {

	public static LinkedList<Relatorio> getRelatorios(int idTeen) throws SQLException, ClassNotFoundException, IOException {
		Connection connect = new Factory().getConnection();		
		
		String consult = "SELECT * FROM relatorios WHERE rel_idteen=\'"+idTeen+"\'";			
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);

		LinkedList<Relatorio> result = new LinkedList<Relatorio>();
		
		while(resultSet.next()) {
			Relatorio it;
			
			int tipo = resultSet.getInt(2);
			int extId = resultSet.getInt(6);
			boolean fech = resultSet.getBoolean(3);
			int idFech = resultSet.getInt(4);
			
			ResultSet aux;
			Fechamento fechamento;
			if(fech) {
				fechamento =new fechamentoDao().getFechamento(idFech);
			}else {
				fechamento = null;
			}
			
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
				it = new RelatorioArena(fechamento, null, presenca, meditacao,
						pontualidade, anotDomingo, anotArena, versiculos, pontosExtras);
				break;
			case 1:
				consult = "SELECT * FROM rel_imersao WHERE imer_rel_id=\'"+extId+"\'";			
				aux = statement.executeQuery(consult);
				boolean presenca2 	= aux.getBoolean(2);
				int pontosExtras2	= aux.getInt(3);
				it = new RelatorioImersao(fechamento, null,	presenca2, pontosExtras2);
				break;
			default: return null;
			}
			result.add(it);					
		}
		return result;
	}
	
	public static LinkedList<Relatorio> getRelatoriosFechamento(int idFech) throws SQLException, ClassNotFoundException, IOException {
		Connection connect = new Factory().getConnection();		
		
		String consult = "SELECT * FROM relatorios WHERE rel_idfech=\'"+idFech+"\'";			
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);

		LinkedList<Relatorio> result = new LinkedList<Relatorio>();
		
		Fechamento fechamento = null; //inserir no contrutor do fechamento
		
		while(resultSet.next()) {
			Relatorio it;			
			boolean fech = true;
			int tipo = resultSet.getInt(2);
			int extId = resultSet.getInt(6);
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
				it = new RelatorioArena(fechamento, null, presenca, meditacao,
						pontualidade, anotDomingo, anotArena, versiculos, pontosExtras);
				break;
			case 1:
				consult = "SELECT * FROM rel_imersao WHERE imer_rel_id=\'"+extId+"\'";			
				aux = statement.executeQuery(consult);
				boolean presenca2 	= aux.getBoolean(2);
				int pontosExtras2	= aux.getInt(3);
				it = new RelatorioImersao(fechamento, null,	presenca2, pontosExtras2);
				break;
			default: return null;
			}
			result.add(it);					
		}
		return result;
	}

}
