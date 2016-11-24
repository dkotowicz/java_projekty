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
	private PreparedStatement select_all_nieruchomosci;
	private PreparedStatement select_all_posredniki;
	private PreparedStatement select_all_nieruchomosc_posrednik;
	private PreparedStatement delete_nieruchomosc;
	private PreparedStatement delete_posrednik;
	private PreparedStatement delete_all_nieruchomosci;
	private PreparedStatement delete_all_posredniki;
	private PreparedStatement update_nieruchomosci;
	private PreparedStatement update_posrednik;
	
	private PreparedStatement select_nieruchomosci;

	
	
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
			insert_nieruchomosc = connection.prepareStatement("INSERT INTO nieruchomosc(miasto, kod_pocztowy, czynsz, ulica, nr_bloku, id_posrednik) VALUES (?,?,?,?,?,?)");
			insert_posrednik = connection.prepareStatement("INSERT INTO posrednik(nazwa, regon) VALUES (?,?)");
			update_nieruchomosci = connection.prepareStatement("UPDATE nieruchomosc SET miasto=?, kod_pocztowy=?, czynsz=?, ulica=?, nr_bloku=?, id_posrednik=? WHERE id_nieruchomosc=?");
			update_posrednik = connection.prepareStatement("UPDATE posrednik SET nazwa=?, regon=? WHERE id_posrednik=?");
			
			select_all_nieruchomosci = connection.prepareStatement("SELECT * FROM nieruchomosc");
			select_all_posredniki = connection.prepareStatement("SELECT * FROM posrednik");
			select_all_nieruchomosc_posrednik = connection.prepareStatement("SELECT nieruchomosc.miasto, nieruchomosc.ulica, nieruchomosc.nr_bloku, nieruchomosc.kod_pocztowy FROM nieruchomosc INNER JOIN posrednik ON nieruchomosc.id_posrednik=posrednik.id_posrednik WHERE posrednik.id_posrednik=?");			
			delete_nieruchomosc = connection.prepareStatement("DELETE FROM nieruchomosc WHERE id_nieruchomosc = ?");
			delete_posrednik = connection.prepareStatement("DELETE FROM posrednik WHERE id_posrednik = ?");
			delete_all_nieruchomosci = connection.prepareStatement("DELETE FROM nieruchomosc");
			delete_all_posredniki = connection.prepareStatement("DELETE FROM posrednik");
			
			
			//select_nieruchomosci = connection.prepareStatement("SELECT * FROM nieruchomosc WHERE id_nieruchomosc = ?");

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
	public Connection get_connection() {
		return connection;
	}
    
    public boolean create_all_tables(){

    	String posrednik_table="CREATE TABLE IF NOT EXISTS posrednik ( id_posrednik INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nazwa VARCHAR(100), regon VARCHAR(10))";
    	String nieruchomosc_table="CREATE TABLE IF NOT EXISTS nieruchomosc ( id_nieruchomosc INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, miasto VARCHAR(50), kod_pocztowy VARCHAR(7), czynsz FLOAT, ulica VARCHAR(50), nr_bloku VARCHAR(5), id_posrednik INTEGER, FOREIGN KEY(id_posrednik) REFERENCES posrednik(id_posrednik))";
        try {
        	stat.execute(posrednik_table);
            stat.execute(nieruchomosc_table);
            System.out.println("CREATE_ALL_TABLES dobrze");
        } catch (SQLException e) {
            System.err.println("Wyst�pi� b��d CREATE_ALL_TABLES");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean insert_posrednik(Posrednik posrednik){
		int correct = 0;	
		try {
			insert_posrednik.setString(1, posrednik.get_nazwa());
			insert_posrednik.setString(2, posrednik.get_regon());
			correct = insert_posrednik.executeUpdate();
			System.out.println("insert_posrednik dobrze");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Wyst�pi� b��d insert_nieruchomosc");
		}
		if(correct == 1){return true;}else{return false;}
		}
    
	public boolean insert_nieruchomosc(Nieruchomosc nieruchomosc){
		int correct = 0;	
		try {
			insert_nieruchomosc.setString(1, nieruchomosc.get_miasto());
			insert_nieruchomosc.setString(2, nieruchomosc.get_kod_pocztowy());
			insert_nieruchomosc.setFloat(3, nieruchomosc.get_czynsz());
			insert_nieruchomosc.setString(4, nieruchomosc.get_ulica());
			insert_nieruchomosc.setString(5, nieruchomosc.get_nr_bloku());
			insert_nieruchomosc.setInt(6, nieruchomosc.get_id_posrednik());	
			correct = insert_nieruchomosc.executeUpdate();
			System.out.println("insert_nieruchomosc dobrze");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Wyst�pi� b��d insert_nieruchomosc");
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
	                n.set_id_posrednik(resultSet.getInt("id_posrednik"));
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
	                
	                int id = resultSet.getInt("id_posrednik");
	                String nazwa = resultSet.getString("nazwa");
	                String regon = resultSet.getString("regon");
	                
	                System.out.print("ID: " + id);
	                System.out.print(", nazwa: " + nazwa);
	                System.out.print(", regon: " + regon);
	                all_posrednik.add(p);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("wyst�pi� b�ad select_all_posrednik");
	        }
	        return all_posrednik;
	    }
	 public boolean delete_nieruchomosc(Nieruchomosc id_nieruchomosc){
			int correct = 0;
				try {
					delete_nieruchomosc.setInt(1,id_nieruchomosc.get_id_nieruchomosc());
					delete_nieruchomosc.execute();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Wyst�pi� b��d delete_nieruchomosc");
				}
				if(correct == 1){return true;}else{return false;}
		}
	 public boolean delete_posrednik(Posrednik id_posrednik){
			int correct = 0;
				try {
					delete_posrednik.setInt(1,id_posrednik.get_id_posrednik());
					delete_posrednik.execute();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Wyst�pi� b��d delete_posrednik");
				}
				if(correct == 1){return true;}else{return false;}
		}
	    public boolean delete_all_tables(){
	        try {
	            delete_all_nieruchomosci.execute();
	            delete_all_posredniki.execute();
	        }catch (SQLException e){
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	 
	public boolean update_nieruchomosc(String miasto, String ulica, Float czynsz, String kod_pocztowy, String nr_bloku, int id_nieruchomosc, int id_posrednik) {
		int correct = 0;
		try {
			update_nieruchomosci.setString(1, miasto);
			update_nieruchomosci.setString(2, kod_pocztowy);
			update_nieruchomosci.setFloat(3, czynsz);
			update_nieruchomosci.setString(4, ulica);
			update_nieruchomosci.setString(5, nr_bloku);
			update_nieruchomosci.setInt(6, id_posrednik);
			update_nieruchomosci.setInt(7, id_nieruchomosc);
			
			correct = update_nieruchomosci.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(correct == 1){return true;}else{return false;}
	}
	
	public boolean update_posrednik(String nazwa, String regon, int id_posrednik) {
		int correct = 0;
		try {
			update_posrednik.setString(1, nazwa);
			update_posrednik.setString(2, regon);
			update_posrednik.setInt(3, id_posrednik);
			correct = update_posrednik.executeUpdate();
			System.out.print(correct);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(correct == 1){return true;}else{return false;}
	}
	
	
	/*public List<Posrednik>  select_all_nieruchomosc_posrednik(Nieruchomosc id_posrednik){
    List<Posrednik> select_all_nieruchomosc_posrednik =new ArrayList<>();
    try {
    	select_all_nieruchomosc_posrednik.setInt(1,id_posrednik.get_id_posrednik());
        ResultSet resultSet = select_all_nieruchomosc_posrednik.executeQuery();
        while (resultSet.next()) {
        	Nieruchomosc n1=new Nieruchomosc();
            n1.set_miasto(resultSet.getString("miasto"));
            n1.set_kod_pocztowy(resultSet.getString("kod_pocztowy"));
            n1.set_ulica(resultSet.getString("ulica"));
            n1.set_nr_bloku(resultSet.getString("nr_bloku"));
           
            String miasto = resultSet.getString("miasto");
            String kod_pocztowy = resultSet.getString("kod_pocztowy");
            String ulica = resultSet.getString("ulica");
            String nr_bloku = resultSet.getString("nr_bloku");
            
            System.out.print("miasto: " + miasto);
            System.out.print(", kod_pocztowy: " + kod_pocztowy);
            System.out.print(", ulica: " + ulica);
            System.out.print(", nr_bloku: " + nr_bloku);
            select_all_nieruchomosc_posrednik.add(n1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("wyst�pi� b�ad select_all_nieruochomosc_posrednik");
    }
    return select_all_nieruchomosc_posrednik;
}*/
	
	 
	 
}
