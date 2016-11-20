package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Nieruchomosc;
import models.Posrednik;

public class Crud_posrednik 
{
	private Connection connection;
	private Statement stmt;
	private PreparedStatement insert_posrednik;
	private PreparedStatement delete_posrednik;
	private PreparedStatement delete_all_posredniki;
	private PreparedStatement select_posrednik;
	private PreparedStatement select_all_posredniki;
	private PreparedStatement update_posredniki;
	
	
	public Connection getConnection() {
		Connection connection = null;
		return connection;
	}
	public Crud_posrednik(){
		try{
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			stmt = connection.createStatement();
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("posrednik".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}
			if (!tableExists) stmt.executeUpdate("CREATE TABLE posrednik (nazwa varchar(100), regon varchar(10) UNIQUE, data_zalozenia date");
			insert_posrednik = connection.prepareStatement("INSERT INTO posrednik(nazwa, regon, data_zalozenia) VALUES (?,?,?)");
			delete_posrednik = connection.prepareStatement("DELETE FROM posrednik WHERE regon = ?");
			delete_all_posredniki = connection.prepareStatement("DELETE FROM posrednik");
			select_posrednik = connection.prepareStatement("SELECT * FROM posrednik WHERE regon = ?");
			select_all_posredniki = connection.prepareStatement("SELECT * FROM posrednik");
			update_posredniki = connection.prepareStatement("UPDATE posrednik SET nazwa=?, regon=? data_zalozenia=? WHERE regon=?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean insert_posrednik(Posrednik posrednik){
		int correct = 0;	
		try {
			insert_posrednik.setString(1, posrednik.get_nazwa());
			insert_posrednik.setString(2, posrednik.get_regon());
			insert_posrednik.setDate(3, posrednik.get_data_zalozenia());
			correct = insert_posrednik.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(correct == 1){return true;}else{return false;}
		}
	public boolean delete_posrednik(Posrednik posrednik){
		int correct = 0;
			try {
				delete_posrednik.setString(1, posrednik.get_regon());
				correct = delete_posrednik.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(correct == 1){return true;}else{return false;}
		}
	public boolean delete_all_posredniki(Posrednik posrednik){
		int correct = 0;
			try {
				correct = delete_all_posredniki.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(correct == 1){return true;}else{return false;}
		}
	public boolean update_posrednik(Posrednik stare, Posrednik nowe) {
		int correct = 0;
		try {
			update_posredniki.setString(1, nowe.get_nazwa());
			update_posredniki.setString(2, nowe.get_regon());
			update_posredniki.setDate(3, nowe.get_data_zalozenia());
			update_posredniki.setString(4, stare.get_regon());
			correct = update_posredniki.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(correct == 1){return true;}else{return false;}
	}
}
