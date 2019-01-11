package br.com.tallys.ibel.arena.chamada.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
		if(intCodType != 0) extId = resultSet.getInt(4);
		CodigoType tipo = null;
		
		switch(intCodType) {
		case 0: tipo = CodigoType.Lider; 	break;
		case 1: tipo = CodigoType.Sublider;	break;
		case 2: tipo = CodigoType.Teen; 	break;
		}
		
		Codigo result = new Codigo(Id, codigo, tipo, extId);
		connect.close();//
		return result;

	}
	
	public static Codigo getCodOfGATeen(int id) throws ClassNotFoundException, IOException, SQLException {
		Connection connect = new Factory().getConnection();
		String consult = "SELECT * FROM codigo WHERE cod_extid=\'"+id+"\' AND cod_type=2";
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);
		if(!resultSet.next()) { connect.close(); return null;}
		int Id = resultSet.getInt(1);
		String codigo = resultSet.getString(2);
		int intCodType = resultSet.getInt(3);
		int extId = id;
		if(intCodType != 0) resultSet.getInt(4);
		CodigoType tipo = null;
		
		switch(intCodType) {
		case 0: tipo = CodigoType.Lider; 	break;
		case 1: tipo = CodigoType.Sublider;	break;
		case 2: tipo = CodigoType.Teen; 	break;
		}
		
		Codigo result = new Codigo(Id, codigo, tipo, extId);
		connect.close();
		return result;
	}

	public static void insert(Codigo cd) throws ClassNotFoundException, IOException, SQLException {
		Connection connect = null;
		connect = new Factory().getConnection();			
		
		String consult = "INSERT INTO `codigo`(`cod_cod`, `cod_type`, `cod_extid`) VALUES (?,?,?)";
		PreparedStatement pstmt = connect.prepareStatement(consult);
        pstmt.setString(1, cd.getCod());
        pstmt.setInt(2, cd.getTipo().ordinal());
        pstmt.setInt(3, cd.getExtId());
		pstmt.executeUpdate();		
		connect.close();
		
	}

	public static Codigo getCodOfGASublider(int id) throws ClassNotFoundException, IOException, SQLException {
		Connection connect = new Factory().getConnection();
		String consult = "SELECT * FROM codigo WHERE cod_extid=\'"+id+"\' AND cod_type=1";
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);
		if(!resultSet.next()) { connect.close(); return null;}
		int Id = resultSet.getInt(1);
		String codigo = resultSet.getString(2);
		int intCodType = resultSet.getInt(3);
		int extId = id;
		if(intCodType != 0) resultSet.getInt(4);
		CodigoType tipo = null;
		
		switch(intCodType) {
		case 0: tipo = CodigoType.Lider; 	break;
		case 1: tipo = CodigoType.Sublider;	break;
		case 2: tipo = CodigoType.Teen; 	break;
		}
		
		Codigo result = new Codigo(Id, codigo, tipo, extId);
		connect.close();
		return result;
	}

}
