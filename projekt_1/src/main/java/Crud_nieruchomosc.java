package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Crud_nieruchomosc 
{
	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DB_URL = "jdbc:sqlite:biuro.db";
	private Connection connection;
	private Statement stat;
	private PreparedStatement insert_nieruchomosc;
	private PreparedStatement insert_posrednik;
	private PreparedStatement delete_nieruchomosc;
	private PreparedStatement delete_all_nieruchomosci;
	private PreparedStatement select_nieruchomosci;
	private PreparedStatement select_all_nieruchomosci;
	private PreparedStatement select_all_posredniki;
	private PreparedStatement update_nieruchomosci;
	
    public Crud_nieruchomosc(){
        try {
            Class.forName(DRIVER);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
		try{
			connection = DriverManager.getConnection(DB_URL);
            stat = connection.createStatement();
            create_all_tables();
			insert_nieruchomosc = connection.prepareStatement("INSERT INTO nieruchomosc(miasto, kod_pocztowy, czynsz, ulica, nr_bloku) VALUES (?,?,?,?,?)");
			insert_posrednik = connection.prepareStatement("INSERT INTO posrednik(nazwa, regon, id_nieruchomosc) VALUES (?,?,?)");
			delete_nieruchomosc = connection.prepareStatement("DELETE FROM nieruchomosc WHERE id_nieruchomosc = ?");
			delete_all_nieruchomosci = connection.prepareStatement("DELETE FROM nieruchomosc");
			select_nieruchomosci = connection.prepareStatement("SELECT * FROM nieruchomosc WHERE id_nieruchomosc = ?");
			select_all_nieruchomosci = connection.prepareStatement("SELECT * FROM nieruchomosc");
			select_all_posredniki = connection.prepareStatement("SELECT * FROM posrednik");
			update_nieruchomosci = connection.prepareStatement("UPDATE nieruchomosc SET miasto=?, kod_pocztowy=?, czynsz=?, ulica=?, nr_bloku=? WHERE id_nieruchomosc=?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public boolean create_all_tables(){

        String nieruchomosc_table="CREATE TABLE IF NOT EXISTS nieruchomosc ( id_nieruchomosc INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, miasto VARCHAR(50), kod_pocztowy VARCHAR(7), czynsz FLOAT, ulica VARCHAR(50), nr_bloku VARCHAR(5))";
        String posrednik_table="CREATE TABLE IF NOT EXISTS posrednik ( id_posrednik INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nazwa VARCHAR(100), regon VARCHAR(10), id_nieruchomosc INTEGER,FOREIGN KEY(id_nieruchomosc) REFERENCES nieruchomosc(id_nieruchomosc))";

        try {
            stat.execute(nieruchomosc_table);
            stat.execute(posrednik_table);
            System.out.println("CREATE_ALL_TABLES dobrze");
        } catch (SQLException e) {
            System.err.println("Wyst�pi� b��d CREATE_ALL_TABLES");
            e.printStackTrace();
            return false;
        }
        return true;
    }
	
	public boolean insert_nieruchomosc(Nieruchomosc nieruchomosc){
		int correct = 0;	
		try {
			insert_nieruchomosc.setString(1, nieruchomosc.get_miasto());
			insert_nieruchomosc.setString(2, nieruchomosc.get_kod_pocztowy());
			insert_nieruchomosc.setFloat(3, nieruchomosc.get_czynsz());
			insert_nieruchomosc.setString(4, nieruchomosc.get_ulica());
			insert_nieruchomosc.setString(5, nieruchomosc.get_nr_bloku());
			correct = insert_nieruchomosc.executeUpdate();
			System.out.println("insert_nieruchomosc dobrze");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Wyst�pi� b��d insert_nieruchomosc");
		}
		if(correct == 1){return true;}else{return false;}
		}
	
	public boolean insert_posrednik(Posrednik posrednik){
		int correct = 0;	
		try {
			insert_posrednik.setString(1, posrednik.get_nazwa());
			insert_posrednik.setString(2, posrednik.get_regon());
			insert_posrednik.setInt(3, posrednik.get_id_nieruchomosc());
			correct = insert_posrednik.executeUpdate();
			System.out.println("insert_posrednik dobrze");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Wyst�pi� b��d insert_nieruchomosc");
		}
		if(correct == 1){return true;}else{return false;}
		}
	
	public boolean delete_nieruchomosc(Nieruchomosc nieruchomosc){
		int correct = 0;
			try {
				delete_nieruchomosc.setInt(1, nieruchomosc.get_id_nieruchomosc());
				correct = delete_nieruchomosc.executeUpdate();
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
			update_nieruchomosci.setInt(5, stare.get_id_nieruchomosc());
			correct = update_nieruchomosci.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(correct == 1){return true;}else{return false;}
	}
	
	 public List<Nieruchomosc> select_all_nieruchomosc(){
	        List<Nieruchomosc> all_nieruchomosc =new ArrayList<>();
	        try {
	            ResultSet resultSet = select_all_nieruchomosci.executeQuery();
	            while (resultSet.next()) {
	            	Nieruchomosc n=new Nieruchomosc();
	                n.set_id_nieruchomosc(resultSet.getInt("id_nieruchomosc"));
	                n.set_miasto(resultSet.getString("miasto"));
	                n.set_kod_pocztowy(resultSet.getString("kod_pocztowy"));
	                n.set_czynsz(resultSet.getFloat("czynsz"));
	                n.set_ulica(resultSet.getString("ulica"));
	                n.set_nr_bloku(resultSet.getString("nr_bloku"));
	                int id = resultSet.getInt("id_nieruchomosc");
	                String miasto = resultSet.getString("miasto");
	                String kod_pocztowy = resultSet.getString("kod_pocztowy");
	                
	                System.out.print("ID: " + id);
	                System.out.print(", Miasto: " + miasto);
	                System.out.print(", Kod_pocztowy: " + kod_pocztowy);
	                all_nieruchomosc.add(n);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("wyst�pi� b�ad select_all_nieruchomosc");
	        }
	        return all_nieruchomosc;
	    }
	 
	 public List<Posrednik> select_all_posrednik(){
	        List<Posrednik> all_posrednik =new ArrayList<>();
	        try {
	            ResultSet resultSet = select_all_posredniki.executeQuery();
	            while (resultSet.next()) {
	            	Posrednik p=new Posrednik();
	                p.set_id_posrednik(resultSet.getInt("id_posrednik"));
	                p.set_nazwa(resultSet.getString("nazwa"));
	                p.set_regon(resultSet.getString("regon"));
	                p.set_id_nieruchomosc(resultSet.getInt("id_nieruchomosc"));
	                
	                int id = resultSet.getInt("id_posrednik");
	                String nazwa = resultSet.getString("nazwa");
	                String regon = resultSet.getString("regon");
	                int id_nieruchomosc = resultSet.getInt("id_nieruchomosc");
	                
	                System.out.print("ID: " + id);
	                System.out.print(", nazwa: " + nazwa);
	                System.out.print(", regon: " + regon);
	                System.out.println(", id_nieruchomosc: "+id_nieruchomosc);
	                all_posrednik.add(p);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("wyst�pi� b�ad select_all_posrednik");
	        }
	        return all_posrednik;
	    }
}
