package main.java;

import java.util.List;

public class sprawdz {

	public static void main(String[] args) {
		Crud_nieruchomosc nier = new Crud_nieruchomosc();
		nier.create_all_tables();
		Nieruchomosc n = new Nieruchomosc();
		//insert
		n.set_miasto("Warszawa");
		n.set_kod_pocztowy("12-345");
		n.set_czynsz((float) 45.65);
		n.set_nr_bloku("45");
		n.set_ulica("Słowackiego");
		nier.insert_nieruchomosc(n);
		//delete
		Nieruchomosc n1 = new Nieruchomosc();
		n1.set_miasto("Gdańsk");
		n1.set_kod_pocztowy("12-345");
		n1.set_czynsz((float) 45.65);
		n1.set_nr_bloku("45");
		n1.set_ulica("Słowackiego");
		nier.insert_nieruchomosc(n1);
		nier.delete_nieruchomosc(n);
		//delete_all
		//select
		//select_all
		nier.select_all_nieruchomosc();
		//update
		
		
	}

}
