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

    public Factory() {
    }

    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }

        return instance;
    }

    public void readProperties() throws IOException {
        Properties properties = new Properties();

        try {
            String path = "jdbc/datasource.properties";
            
            //teste
            new File(path);
            
            ClassLoader aux = this.getClass().getClassLoader();
            InputStream input = aux.getResourceAsStream(path);
            if(input == null) {
            	dbHost = "localhost";
	            dbPort = "5432";
	            dbName = "arenateens-chamada";
	            dbUser = "root";
	            dbPassword = "";
            }else {
	            properties.load(input);
	            dbHost = properties.getProperty("host");
	            dbPort = properties.getProperty("port");
	            dbName = properties.getProperty("name");
	            dbUser = properties.getProperty("user");
	            dbPassword = properties.getProperty("password");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());

            throw new IOException("Erro ao obter informações do banco de dados.");
        }
    }

    public Connection getConnection() throws ClassNotFoundException, IOException, SQLException {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            readProperties();
  
          //String url = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
            String url = "jdbc:mysql://" + dbHost + "/" + dbName + "?useTimezone=true&serverTimezone=UTC";
            
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());

            throw new ClassNotFoundException("Erro de conexão ao banco de dados.");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro de conexão ao banco de dados.");
        }

        return connection;
    }
	
}