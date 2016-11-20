package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Crud_nieruchomosc 
{
	private Connection connection;
	private Statement stmt;
	private PreparedStatement Dodaj_nieruchomosc;
	private PreparedStatement Usun_nieruchomosc;
	private PreparedStatement Wyswietl_wszystkie_nieruchomosci;
	
	
	public Connection getConnection() {
		Connection conn = null;
		return conn;
	}
	public Crud_nieruchomosc(){
		try{
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			stmt = connection.createStatement();
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("nieruchomosc".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}
			if (!tableExists) stmt.executeUpdate("");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
