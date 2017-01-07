package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Nieruchomosc;
import com.example.shdemo.domain.Posrednik;

public interface SellingManager {
	
	void addClient(Posrednik person);
	List<Posrednik> getAllClients();
	void deleteClient(Posrednik person);
	Posrednik findClientByPin(String pin);
	
	Long addNewCar(Nieruchomosc car);
	List<Nieruchomosc> getAvailableCars();
	void disposeCar(Posrednik person, Nieruchomosc car);
	Nieruchomosc findCarById(Long id);

	List<Nieruchomosc> getOwnedCars(Posrednik person);
	void sellCar(Long personId, Long carId);

}
