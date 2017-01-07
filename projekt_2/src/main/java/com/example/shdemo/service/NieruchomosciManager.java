package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Posrednik;
import com.example.shdemo.domain.Nieruchomosc;

public interface NieruchomosciManager 
{	
	void addNieruchomosc(Nieruchomosc nieruchomosc);
	Long addPosrednik(Posrednik posrednik);
}
