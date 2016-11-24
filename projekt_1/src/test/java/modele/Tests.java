
package test.java.modele;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.Crud_nieruchomosc;
import main.java.Nieruchomosc;
import main.java.Posrednik;

public class Tests {
	
	Crud_nieruchomosc test = new Crud_nieruchomosc();
	
	@Before
	public void drop_tables()
	{
		test.delete_all_tables();
		test.create_all_tables();
	}
	
	@After
	public void delete_all()
	{
		test.delete_all_tables();
	}
	
	//po��czenie 
	@Test
	public void test_connection()
	{
		assertNotNull((test).get_connection());
	}
	
	//dodawanie
	@Test
	public void add_posrednik()
	{
		Posrednik p = new Posrednik();
		p.set_nazwa("Biuro nieruchomosci");
		p.set_regon("1233265786");
		assertEquals(true,test.insert_posrednik(p));
		
		List<Posrednik> posred = test.select_all_posrednik();
		Posrednik add_posrednik = posred.get(0);
		assertEquals("1233265786",add_posrednik.get_regon());
	}
	@Test
	public void add_nieruchomosc()
	{
		Nieruchomosc n = new Nieruchomosc();
		n.set_miasto("Gda�sk");
		n.set_kod_pocztowy("80-292");
		n.set_ulica("G�ralska");
		n.set_czynsz(560);
		n.set_nr_bloku("41D");
		assertEquals(true,test.insert_nieruchomosc(n));
		
		List<Nieruchomosc> nier = test.select_all_nieruchomosc();
		Nieruchomosc add_nieruchomosc = nier.get(0);
		assertEquals("G�ralska",add_nieruchomosc.get_ulica());
	}
	
	//edycja
	@Test
	public void update_posrednik()
	{
		Posrednik p = new Posrednik();
		p.set_nazwa("Darek R�kawek");
		p.set_regon("1233265788");
		assertEquals(true,test.insert_posrednik(p));
		
		List<Posrednik> posred = test.select_all_posrednik();
		Posrednik add_posrednik = posred.get(0);
		assertEquals("1233265788",add_posrednik.get_regon());
		
		assertEquals(true, test.update_posrednik("Dariusz","2233265788", add_posrednik.get_id_posrednik()));
	}
	@Test
	public void update_nieruchomosc()
	{
		Nieruchomosc n = new Nieruchomosc();
		n.set_miasto("Gda�sk");
		n.set_kod_pocztowy("80-292");
		n.set_ulica("G�ralska");
		n.set_czynsz(560);
		n.set_nr_bloku("41D");
		n.set_id_posrednik(10);
		assertEquals(true,test.insert_nieruchomosc(n));
		
		List<Nieruchomosc> nier = test.select_all_nieruchomosc();
		Nieruchomosc add_nieruchomosc = nier.get(0);
		assertEquals("G�ralska",add_nieruchomosc.get_ulica());
		
		assertEquals(true, test.update_nieruchomosc("Warszawa", "S�owackiego", (float) 677, "90-823", "54A", add_nieruchomosc.get_id_nieruchomosc(),0));
	}
	
	//usuwanie
	@Test
	public void delete_posrednik()
	{
		Posrednik p = new Posrednik();
		p.set_nazwa("Darek R�kawek");
		p.set_regon("1233265788");
		assertEquals(true,test.insert_posrednik(p));
		
		List<Posrednik> posred = test.select_all_posrednik();
		Posrednik add_posrednik = posred.get(0);
		assertEquals("1233265788",add_posrednik.get_regon());

		assertEquals(true,test.delete_posrednik(add_posrednik.get_id_posrednik()));
	}
	@Test
	public void delete_nieruchomosc()
	{
		Nieruchomosc n = new Nieruchomosc();
		n.set_miasto("Gda�sk");
		n.set_kod_pocztowy("80-292");
		n.set_ulica("G�ralska");
		n.set_czynsz(560);
		n.set_nr_bloku("41D");
		n.set_id_posrednik(1);
		assertEquals(true,test.insert_nieruchomosc(n));
		
		List<Nieruchomosc> nier = test.select_all_nieruchomosc();
		Nieruchomosc add_nieruchomosc = nier.get(0);
		assertEquals("G�ralska",add_nieruchomosc.get_ulica());
		
		assertEquals(true,test.delete_nieruchomosc(add_nieruchomosc.get_id_nieruchomosc()));
	}
	@Test 
	public void delete_all_tables()
	{
		Posrednik p = new Posrednik();
		p.set_nazwa("Darek R�kawek");
		p.set_regon("1233265788");
		assertEquals(true,test.insert_posrednik(p));
		
		List<Posrednik> posred = test.select_all_posrednik();
		Posrednik add_posrednik = posred.get(0);
		assertEquals("1233265788",add_posrednik.get_regon());
		
		Nieruchomosc n = new Nieruchomosc();
		n.set_miasto("Gda�sk");
		n.set_kod_pocztowy("80-292");
		n.set_ulica("G�ralska");
		n.set_czynsz(560);
		n.set_nr_bloku("41D");
		n.set_id_posrednik(p.get_id_posrednik());
		assertEquals(true,test.insert_nieruchomosc(n));
		
		List<Nieruchomosc> nier = test.select_all_nieruchomosc();
		Nieruchomosc add_nieruchomosc = nier.get(0);
		assertEquals("G�ralska",add_nieruchomosc.get_ulica());
		
		assertEquals(true,test.delete_all_tables());
	}
	
	//select_all 
	@Test 
	public void select_posrednik_all()
	{
		Posrednik p = new Posrednik();
		p.set_nazwa("Darek R�kawek");
		p.set_regon("1233265788");
		assertEquals(true,test.insert_posrednik(p));
		
		Posrednik p1 = new Posrednik();
		p1.set_nazwa("Dariusz");
		p1.set_regon("2233265788");
		assertEquals(true,test.insert_posrednik(p1));
		
		Posrednik p2 = new Posrednik();
		p2.set_nazwa("Nieruchomosc");
		p2.set_regon("2233265789");
		assertEquals(true,test.insert_posrednik(p2));
		
		List<Posrednik> posrednik = test.select_all_posrednik();
		assertEquals(3,posrednik.size());
		
		Posrednik add_posrednik_0 = posrednik.get(0);
		assertEquals("Darek R�kawek",add_posrednik_0.get_nazwa());
		assertEquals("1233265788",add_posrednik_0.get_regon());
		
		Posrednik add_posrednik_1 = posrednik.get(1);
		assertEquals("Dariusz",add_posrednik_1.get_nazwa());
		assertEquals("2233265788",add_posrednik_1.get_regon());
		
		Posrednik add_posrednik_2 = posrednik.get(2);
		assertEquals("Nieruchomosc",add_posrednik_2.get_nazwa());
		assertEquals("2233265789",add_posrednik_2.get_regon());
	}
	@Test 
	public void select_nieruchomosc_all()
	{
		Posrednik p = new Posrednik();
		p.set_nazwa("Darek R�kawek");
		p.set_regon("1233265788");
		assertEquals(true,test.insert_posrednik(p));
		
		Posrednik p1 = new Posrednik();
		p1.set_nazwa("Dariusz");
		p1.set_regon("2233265788");
		assertEquals(true,test.insert_posrednik(p1));
		
		Posrednik p2 = new Posrednik();
		p2.set_nazwa("Nieruchomosc");
		p2.set_regon("2233265789");
		assertEquals(true,test.insert_posrednik(p2));
		
		Nieruchomosc n = new Nieruchomosc();
		n.set_miasto("Gda�sk");
		n.set_kod_pocztowy("80-292");
		n.set_ulica("G�ralska");
		n.set_czynsz(560);
		n.set_nr_bloku("41D");
		n.set_id_posrednik(p.get_id_posrednik());
		assertEquals(true,test.insert_nieruchomosc(n));
		
		Nieruchomosc n1 = new Nieruchomosc();
		n1.set_miasto("Warszawa");
		n1.set_kod_pocztowy("90-292");
		n1.set_ulica("S�owackiego");
		n1.set_czynsz(560);
		n1.set_nr_bloku("8");
		n1.set_id_posrednik(p2.get_id_posrednik());
		assertEquals(true,test.insert_nieruchomosc(n1));
		
		Nieruchomosc n2 = new Nieruchomosc();
		n2.set_miasto("Krak�w");
		n2.set_kod_pocztowy("70-292");
		n2.set_ulica("Podkarpacka");
		n2.set_czynsz(560);
		n2.set_nr_bloku("4");
		n2.set_id_posrednik(p1.get_id_posrednik());
		assertEquals(true,test.insert_nieruchomosc(n2));	
		
		List<Nieruchomosc> nier = test.select_all_nieruchomosc();
		assertEquals(3,nier.size());
		
		Nieruchomosc add_nieruchomosc_0 = nier.get(0);
		assertEquals("Gda�sk",add_nieruchomosc_0.get_miasto());
		assertEquals(p.get_id_posrednik(),add_nieruchomosc_0.get_id_posrednik());
		
		Nieruchomosc add_nieruchomosc_1 = nier.get(1);
		assertEquals("Warszawa",add_nieruchomosc_1.get_miasto());
		assertEquals(p2.get_id_posrednik(),add_nieruchomosc_1.get_id_posrednik());
		
		Nieruchomosc add_nieruchomosc_2 = nier.get(2);
		assertEquals("Krak�w",add_nieruchomosc_2.get_miasto());
		assertEquals(p1.get_id_posrednik(),add_nieruchomosc_2.get_id_posrednik());
	}
	
	//select_one
	@Test
	public void select_posrednik()
	{
		Posrednik p = new Posrednik();
		p.set_nazwa("Darek R�kawek");
		p.set_regon("1233265788");
		assertEquals(true,test.insert_posrednik(p));
		
		Posrednik p1 = new Posrednik();
		p1.set_nazwa("Dariusz");
		p1.set_regon("2233265788");
		assertEquals(true,test.insert_posrednik(p1));

		List<Posrednik> posrednik = test.select_all_posrednik();
		assertEquals(2,posrednik.size());
		Posrednik add_posrednik_0 = posrednik.get(0);
		
		List<Posrednik> posrednik_one = test.select_posrednik(add_posrednik_0.get_id_posrednik());

		assertEquals(1,posrednik_one.size());
	}

	
	
	
	


	

}
