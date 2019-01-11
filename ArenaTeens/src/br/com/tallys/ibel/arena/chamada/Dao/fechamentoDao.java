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

public class fechamentoDao {

	public static Fechamento getFechamento(int idFech) throws SQLException, ClassNotFoundException, IOException {
		Connection connect = new Factory().getConnection();		
		String consult = "SELECT * FROM fechamento WHERE fech_id=\'"+idFech+"\'";			
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);
		
		Date inic = resultSet.getDate(2);
		Date end  = resultSet.getDate(3);
		int tipo = resultSet.getInt(4)==0?0:1;
		LinkedList<Relatorio> relatorios;
		relatorios = tipo==0?RelatorioDao.getRelatoriosFechamentoAnual(idFech):new RelatorioDao().getRelatoriosFechamentoBim(idFech);
		
		Fechamento result = new Fechamento();
		for(int i=0; i<relatorios.size(); i++) {
			relatorios.get(i).setFechamento(result);
		}
		result.update(inic, end, tipo, relatorios);
		
		connect.close();
		return result;
	}



}
