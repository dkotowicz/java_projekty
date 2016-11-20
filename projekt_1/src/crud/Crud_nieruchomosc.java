package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Nieruchomosc;

public class Crud_nieruchomosc 
{
	private Connection connection;
	private Statement stmt;
	private PreparedStatement insert_nieruchomosc;
	private PreparedStatement delete_nieruchomosc;
	private PreparedStatement delete_all_nieruchomosci;
	private PreparedStatement select_nieruchomosci;
	private PreparedStatement select_all_nieruchomosci;
	private PreparedStatement update_nieruchomosci;
	
	
	public Connection getConnection() {
		Connection connection = null;
		return connection;
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
			if (!tableExists) stmt.executeUpdate("CREATE TABLE nieruchomosc (nr varchar(4) UNIQUE, miasto varchar(50), kod_pocztowy varchar(6) , czynsz float, ulica varchar(50), nr_bloku varchar(5))");
			insert_nieruchomosc = connection.prepareStatement("INSERT INTO nieruchomosc(nr, miasto, kod_pocztowy, czynsz, ulica, nr_bloku) VALUES (?,?,?,?,?,?)");
			delete_nieruchomosc = connection.prepareStatement("DELETE FROM nieruchomosc WHERE nr = ?");
			delete_all_nieruchomosci = connection.prepareStatement("DELETE FROM nieruchomosc");
			select_nieruchomosci = connection.prepareStatement("SELECT * FROM nieruchomosc WHERE nr = ?");
			select_all_nieruchomosci = connection.prepareStatement("SELECT * FROM nieruchomosc");
			update_nieruchomosci = connection.prepareStatement("UPDATE nieruchomosc SET miasto=?, kod_pocztowy=?, czynsz=?, ulica=?, nr_bloku=? WHERE nr=?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean insert_nieruchomosc(Nieruchomosc nieruchomosc){
		int correct = 0;	
		try {
			insert_nieruchomosc.setString(1, nieruchomosc.get_nr());
			insert_nieruchomosc.setString(2, nieruchomosc.get_miasto());
			insert_nieruchomosc.setString(3, nieruchomosc.get_kod_pocztowy());
			insert_nieruchomosc.setFloat(4, nieruchomosc.get_czynsz());
			insert_nieruchomosc.setString(5, nieruchomosc.get_ulica());
			insert_nieruchomosc.setString(6, nieruchomosc.get_nr_bloku());
			correct = insert_nieruchomosc.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(correct == 1){return true;}else{return false;}
		}
	public boolean delete_nieruchomosc(Nieruchomosc nieruchomosc){
		int correct = 0;
			try {
				delete_nieruchomosc.setString(1, nieruchomosc.get_nr());
				correct = delete_nieruchomosc.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(correct == 1){return true;}else{return false;}
		}
	public boolean delete_all_nieruchomosci(Nieruchomosc nieruchomosc){
		int correct = 0;
			try {
				
				correct = delete_all_nieruchomosci.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(correct == 1){return true;}else{return false;}
		}
	public boolean update_nieruchomosc(Nieruchomosc stare, Nieruchomosc nowe) {
		int correct = 0;
		try {
			update_nieruchomosci.setString(1, nowe.get_miasto());
			update_nieruchomosci.setString(2, nowe.get_kod_pocztowy());
			update_nieruchomosci.setFloat(3, nowe.get_czynsz());
			update_nieruchomosci.setString(4, nowe.get_ulica());
			update_nieruchomosci.setString(5, nowe.get_nr_bloku());
			update_nieruchomosci.setString(5, stare.get_nr());
			correct = update_nieruchomosci.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(correct == 1){return true;}else{return false;}
	}
	
}
