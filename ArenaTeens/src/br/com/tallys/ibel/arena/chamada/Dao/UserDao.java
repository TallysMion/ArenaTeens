package br.com.tallys.ibel.arena.chamada.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.tallys.ibel.arena.chamada.jdbc.Factory;
import br.com.tallys.ibel.arena.chamada.model.User;

public class UserDao {

	
	public User login(String login, String senha) {
		Connection conect = null;
		try {
			conect = new Factory().getConnection();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//exemplo de execução
		PreparedStatement ps = null;
		try {
		    conect.setAutoCommit(false);
		    String sql = "INSERT INTO USER (login,senha,tipo, id) Values (\"Tomion\", \"senha\", \"0\", \"0\")";
		    ps = conect.prepareStatement(sql);
		    ps.executeUpdate();		     
		    //Grava as informações se caso de problema os dados não são gravados 
		    conect.commit();
		 
		} catch (SQLException e ) {
		    if (conect != null) {
		        try {
		            System.err.print("Rollback efetuado na transação");
		            conect.rollback();
		        } catch(SQLException e2) {
		            System.err.print("Erro na transação!"+e2);
		        }
		    }
		} finally {
		    if (ps != null) {
		        try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    try {
				conect.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//fim do exemplo
		
		
		return null;
	}
	
	
}
