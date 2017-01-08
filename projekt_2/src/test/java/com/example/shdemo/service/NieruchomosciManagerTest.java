package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Posrednik;
import com.example.shdemo.domain.Nieruchomosc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class NieruchomosciManagerTest {

	@Autowired
	NieruchomosciManager nieruchomosciManager;

	private final String NAZWA_1 = "Posrednik1";
	private final String REGON_1 = "123456455";

	private final String NAZWA_2 = "Posrednik2";
	private final String REGON_2 = "123432223";
	
	private final String MIASTO_1 = "Gdansk";
	private final String kodPocztowy_1 = "80-292";
	private final String ulica_1 = "Goralska";
	private final String nrBloku_1 = "41D";

	private final String MIASTO_2 = "Warszawa";
	private final String kodPocztowy_2 = "20-768";
	private final String ulica_2 = "Slowackiego";
	private final String nrBloku_2 = "37A";
	
	@Test
	public void addPosrednik()
	{
		Posrednik posrednik1 = new Posrednik();
		posrednik1.setNazwa(NAZWA_1);
		posrednik1.setRegon(REGON_1);

		Long posrednikId = nieruchomosciManager.addPosrednik(posrednik1);
		Posrednik retrievedPosrednik1 = nieruchomosciManager.findPosrednikById(posrednikId);
		assertEquals(NAZWA_1, retrievedPosrednik1.getNazwa());
		assertEquals(REGON_1, retrievedPosrednik1.getRegon());
	}
	@Test public void addNieruchomosc()
	{
		Nieruchomosc nieruchomosc1 = new Nieruchomosc();
		nieruchomosc1.setMiasto(MIASTO_1);
		nieruchomosc1.setKodPocztowy(kodPocztowy_1);
		nieruchomosc1.setUlica(ulica_1);
		nieruchomosc1.setNrBloku(nrBloku_1);
		nieruchomosciManager.addNieruchomosc(nieruchomosc1);
		
		Nieruchomosc retrievedNieruchomosc1 = nieruchomosciManager.findNieruchomoscbyMiasto(MIASTO_1);
		assertEquals(MIASTO_1, retrievedNieruchomosc1.getMiasto());
		assertEquals(kodPocztowy_1, retrievedNieruchomosc1.getKodPocztowy());
	}
	
	@Test
	public void getAllPosrednik()
	{
		Posrednik posrednik1 = new Posrednik();
		posrednik1.setNazwa(NAZWA_1);
		posrednik1.setRegon(REGON_1);
		Long posrednikId1 = nieruchomosciManager.addPosrednik(posrednik1);
		
		Posrednik posrednik2 = new Posrednik();
		posrednik2.setNazwa(NAZWA_2);
		posrednik2.setRegon(REGON_2);
		Long posrednikId2 = nieruchomosciManager.addPosrednik(posrednik2);
		
		List<Posrednik> posrednik = nieruchomosciManager.getAllPosredniki();
		assertEquals(2,posrednik.size());
		
		assertEquals(NAZWA_1, posrednik.get(0).getNazwa());
		assertEquals(NAZWA_2, posrednik.get(1).getNazwa());		
	}
	
	@Test 
	public void getAllNieruchomosc()
	{
		Nieruchomosc nieruchomosc1 = new Nieruchomosc();
		nieruchomosc1.setMiasto(MIASTO_1);
		nieruchomosc1.setKodPocztowy(kodPocztowy_1);
		nieruchomosc1.setUlica(ulica_1);
		nieruchomosc1.setNrBloku(nrBloku_1);
		nieruchomosciManager.addNieruchomosc(nieruchomosc1);
		
		Nieruchomosc nieruchomosc2 = new Nieruchomosc();
		nieruchomosc2.setMiasto(MIASTO_2);
		nieruchomosc2.setKodPocztowy(kodPocztowy_2);
		nieruchomosc2.setUlica(ulica_2);
		nieruchomosc2.setNrBloku(nrBloku_2);
		nieruchomosciManager.addNieruchomosc(nieruchomosc2);
		
		List<Nieruchomosc> nieruchomosc = nieruchomosciManager.getAllNieruchmosci();
		assertEquals(2,nieruchomosc.size());
		
		assertEquals(ulica_1, nieruchomosc.get(0).getUlica());
		assertEquals(ulica_2, nieruchomosc.get(1).getUlica());
	}
	
	@Test
	public void editNieruchomosc()
	{
		Nieruchomosc nieruchomosc1 = new Nieruchomosc();
		nieruchomosc1.setMiasto(MIASTO_1);
		nieruchomosc1.setKodPocztowy(kodPocztowy_1);
		nieruchomosc1.setUlica(ulica_1);
		nieruchomosc1.setNrBloku(nrBloku_1);
		nieruchomosciManager.addNieruchomosc(nieruchomosc1);
		
		Nieruchomosc retrievedNieruchomosc1 = nieruchomosciManager.findNieruchomoscbyMiasto(MIASTO_1);
		Long nieruchomoscId1 = retrievedNieruchomosc1.getId();
		
		Nieruchomosc nieruchomosc2 = new Nieruchomosc();
		nieruchomosc2.setMiasto(MIASTO_2);
		nieruchomosc2.setKodPocztowy(kodPocztowy_2);
		nieruchomosc2.setUlica(ulica_2);
		nieruchomosc2.setNrBloku(nrBloku_2);
		nieruchomosciManager.addNieruchomosc(nieruchomosc2);
		
		Nieruchomosc retrievedNieruchomosc2 = nieruchomosciManager.findNieruchomoscbyMiasto(MIASTO_2);
		Long nieruchomoscId2 = retrievedNieruchomosc2.getId();
		
		nieruchomosc1.setUlica("Wita Stwosza");
		nieruchomosciManager.editNieruchomosc(nieruchomosc1);
		
		Nieruchomosc nieruchomoscNew = nieruchomosciManager.findNieruchomoscbyId(nieruchomoscId1);
		assertEquals("Wita Stwosza", nieruchomoscNew.getUlica());
		
		Nieruchomosc nieruchomoscDwa = nieruchomosciManager.findNieruchomoscbyId(nieruchomoscId2);
		assertEquals(ulica_2, nieruchomoscDwa.getUlica());	
	}
	
	@Test
	public void editPosrednik()
	{
		Posrednik posrednik1 = new Posrednik();
		posrednik1.setNazwa(NAZWA_1);
		posrednik1.setRegon(REGON_1);
		Long posrednikId1 = nieruchomosciManager.addPosrednik(posrednik1);
		
		Posrednik posrednik2 = new Posrednik();
		posrednik2.setNazwa(NAZWA_2);
		posrednik2.setRegon(REGON_2);
		Long posrednikId2 = nieruchomosciManager.addPosrednik(posrednik2);
		
		posrednik1 = nieruchomosciManager.findPosrednikbyNazwa(NAZWA_1);
		posrednik1.setNazwa("PosrednikNew");
		Long PosrednikId1 = posrednik1.getId();
		nieruchomosciManager.editPosrednik(posrednik1);
		
		Posrednik posrednikNew = nieruchomosciManager.findPosrednikById(PosrednikId1);
		assertEquals("PosrednikNew", posrednikNew.getNazwa());
		
		Posrednik posrednikDwa = nieruchomosciManager.findPosrednikById(posrednikId2);
		assertEquals(NAZWA_2, posrednikDwa.getNazwa());
	}
	
	@Test
	public void getPosrednikById()
	{
		Posrednik posrednik1 = new Posrednik();
		posrednik1.setNazwa(NAZWA_1);
		posrednik1.setRegon(REGON_1);
		Long posrednikId1 = nieruchomosciManager.addPosrednik(posrednik1);
		
		Posrednik posrednik2 = new Posrednik();
		posrednik2.setNazwa(NAZWA_2);
		posrednik2.setRegon(REGON_2);
		Long posrednikId2 = nieruchomosciManager.addPosrednik(posrednik2);
		
		Posrednik posrednikNew = nieruchomosciManager.findPosrednikById(posrednikId2);
		assertEquals(NAZWA_2, posrednikNew.getNazwa());
	}
	
	@Test public void getNieruchomoscById()
	{

		Nieruchomosc nieruchomosc1 = new Nieruchomosc();
		nieruchomosc1.setMiasto(MIASTO_1);
		nieruchomosc1.setKodPocztowy(kodPocztowy_1);
		nieruchomosc1.setUlica(ulica_1);
		nieruchomosc1.setNrBloku(nrBloku_1);
		nieruchomosciManager.addNieruchomosc(nieruchomosc1);
		
		Nieruchomosc retrievedNieruchomosc1 = nieruchomosciManager.findNieruchomoscbyMiasto(MIASTO_1);
		Long nieruchomoscId1 = retrievedNieruchomosc1.getId();
		
		Nieruchomosc nieruchmoscId1ById = nieruchomosciManager.findNieruchomoscbyId(nieruchomosc1.getId());
		assertEquals(nieruchomosc1.getId(), nieruchmoscId1ById.getId());
	}
	
	@Test
	public void getPosrednikByNazwa()
	{
		Posrednik posrednik1 = new Posrednik();
		posrednik1.setNazwa(NAZWA_1);
		posrednik1.setRegon(REGON_1);
		Long posrednikId1 = nieruchomosciManager.addPosrednik(posrednik1);
		
		Posrednik posrednik2 = new Posrednik();
		posrednik2.setNazwa(NAZWA_2);
		posrednik2.setRegon(REGON_2);
		Long posrednikId2 = nieruchomosciManager.addPosrednik(posrednik2);
		
		Posrednik posrednik3 = new Posrednik();
		posrednik3.setNazwa(NAZWA_1);
		posrednik3.setRegon(REGON_2);
		Long posrednikId3 = nieruchomosciManager.addPosrednik(posrednik3);
		
		List<Posrednik> posrednik = nieruchomosciManager.getAllPosredniki();
		assertEquals(3,posrednik.size());
		
		List<Posrednik> posrednikbyNazwa = new ArrayList<Posrednik>();
		for(Posrednik pos : posrednik)
			if(pos.getNazwa()==NAZWA_1)
				posrednikbyNazwa.add(pos);
		
		assertEquals(2,posrednikbyNazwa.size());
		
		assertEquals(REGON_1, posrednikbyNazwa.get(0).getRegon());
		assertEquals(REGON_2, posrednikbyNazwa.get(1).getRegon());
	}
	
	@Test
	public void getNieruchomoscByMiasto()
	{
		Nieruchomosc nieruchomosc1 = new Nieruchomosc();
		nieruchomosc1.setMiasto(MIASTO_1);
		nieruchomosc1.setKodPocztowy(kodPocztowy_1);
		nieruchomosc1.setUlica(ulica_1);
		nieruchomosc1.setNrBloku(nrBloku_1);
		nieruchomosciManager.addNieruchomosc(nieruchomosc1);
		
		Nieruchomosc retrievedNieruchomosc1 = nieruchomosciManager.findNieruchomoscbyMiasto(MIASTO_1);
		Long nieruchomoscId1 = retrievedNieruchomosc1.getId();
		
		Nieruchomosc nieruchomosc2 = new Nieruchomosc();
		nieruchomosc2.setMiasto(MIASTO_2);
		nieruchomosc2.setKodPocztowy(kodPocztowy_2);
		nieruchomosc2.setUlica(ulica_2);
		nieruchomosc2.setNrBloku(nrBloku_2);
		nieruchomosciManager.addNieruchomosc(nieruchomosc2);
		
		Nieruchomosc retrievedNieruchomosc2 = nieruchomosciManager.findNieruchomoscbyMiasto(MIASTO_2);
		Long nieruchomoscId2 = retrievedNieruchomosc2.getId();
		
		Nieruchomosc nieruchomosc3 = new Nieruchomosc();
		nieruchomosc3.setMiasto(MIASTO_1);
		nieruchomosc3.setKodPocztowy(kodPocztowy_1);
		nieruchomosc3.setUlica(ulica_1);
		nieruchomosc3.setNrBloku(nrBloku_2);
		nieruchomosciManager.addNieruchomosc(nieruchomosc3);
		
		Nieruchomosc retrievedNieruchomosc3 = nieruchomosciManager.findNieruchomoscbyMiasto(MIASTO_1);
		Long nieruchomoscId3 = retrievedNieruchomosc3.getId();
		
		List<Nieruchomosc> nieruchomosc = nieruchomosciManager.getAllNieruchmosci();
		assertEquals(3,nieruchomosc.size());
		
		List<Nieruchomosc> nieruchomoscbyMiasto = new ArrayList<Nieruchomosc>();
		for(Nieruchomosc nier : nieruchomosc)
			if(nier.getMiasto()==MIASTO_1)
				nieruchomoscbyMiasto.add(nier);
		
		assertEquals(2,nieruchomoscbyMiasto.size());
		
		assertEquals(ulica_1, nieruchomoscbyMiasto.get(0).getUlica());
		assertEquals(nrBloku_2, nieruchomoscbyMiasto.get(1).getNrBloku());
	}
}
