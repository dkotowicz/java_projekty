package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Posrednik;
import com.example.shdemo.domain.Nieruchomosc;

public interface NieruchomosciManager 
{	
	void addNieruchomosc(Nieruchomosc nieruchomosc);
	Long addPosrednik(Posrednik posrednik);
	
	List<Nieruchomosc> getAllNieruchmosci();
	List<Posrednik> getAllPosredniki();
	
	public boolean editNieruchomosc(Nieruchomosc nieruchomosc);
	public boolean editPosrednik(Posrednik posrednik);
	
	Nieruchomosc findNieruchomoscbyId(Long id);
	Posrednik findPosrednikById(Long id);
	
	public Nieruchomosc findNieruchomoscbyMiasto(String miasto);
	public Posrednik findPosrednikbyNazwa(String nazwa);
	
}
