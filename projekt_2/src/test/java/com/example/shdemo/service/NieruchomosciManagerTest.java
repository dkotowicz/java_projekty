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
}
