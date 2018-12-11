package br.com.tallys.ibel.arena.chamada.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.tallys.ibel.arena.chamada.jdbc.Factory;
import br.com.tallys.ibel.arena.chamada.utils.Codigo;
import br.com.tallys.ibel.arena.chamada.utils.CodigoType;

public class CodigoDao {
	
	public static Codigo getCodigoDB(String codigo) throws ClassNotFoundException, IOException, SQLException {
		Connection connect = new Factory().getConnection();
		String consult = "SELECT * FROM codigo WHERE cod_cod=\'"+codigo+"\'";
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);
		if(!resultSet.next()) { connect.close(); return null;}
		int Id = resultSet.getInt(1);
		int intCodType = resultSet.getInt(3);
		int extId = -1;
		if(intCodType != 0) resultSet.getInt(4);
		CodigoType tipo = null;
		
		switch(intCodType) {
		case 0: tipo = CodigoType.Lider; 	break;
		case 1: tipo = CodigoType.Sublider;	break;
		case 2: tipo = CodigoType.Teen; 	break;
		}
		
		Codigo result = new Codigo(Id, codigo, tipo, extId);
		
		return result;

	}

}
