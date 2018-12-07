package br.com.tallys.ibel.arena.chamada.jdbc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Factory {
	
    private static Factory instance = null;

    private String dbHost;
    private String dbPort;
    private String dbName;
    private String dbUser;
    private String dbPassword;
    private String dbInstanceName;

    public Factory() {
    }

    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }

        return instance;
    }

    public void readProperties() throws IOException {
	 	dbHost = "localhost";
	    dbPort = "3306";
	    dbName = "arenateens";
	    dbUser = "root";
	    dbPassword = "";
	    dbInstanceName = "arenateste-224811:southamerica-east1:arenateens";
    }

    public Connection getConnection() throws ClassNotFoundException, IOException, SQLException {
        Connection connection = null;

        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            

            readProperties();
  
//            String url = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
            String url = "jdbc:mysql://" + dbHost + "/" + dbName + "?useTimezone=true&serverTimezone=UTC";
//            String url = String.format("jdbc:mysql://%s/%s?cloudSqlInstance=%s&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false",dbHost,	dbName, dbInstanceName);
            
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());

            throw new ClassNotFoundException("Classe nao encontrada.");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro de conexão ao banco de dados.");
        }

        return connection;
    }
	
}