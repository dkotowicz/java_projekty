package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Nieruchomosc;
import com.example.shdemo.domain.Posrednik;

@Component
@Transactional
public class SellingMangerHibernateImpl implements SellingManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addClient(Posrednik person) {
		person.setId(null);
		sessionFactory.getCurrentSession().persist(person);
	}
	
	@Override
	public void deleteClient(Posrednik person) {
		person = (Posrednik) sessionFactory.getCurrentSession().get(Posrednik.class,
				person.getId());
		
		// lazy loading here
		for (Nieruchomosc car : person.getCars()) {
			car.setSold(false);
			sessionFactory.getCurrentSession().update(car);
		}
		sessionFactory.getCurrentSession().delete(person);
	}

	@Override
	public List<Nieruchomosc> getOwnedCars(Posrednik person) {
		person = (Posrednik) sessionFactory.getCurrentSession().get(Posrednik.class,
				person.getId());
		// lazy loading here - try this code without (shallow) copying
		List<Nieruchomosc> cars = new ArrayList<Nieruchomosc>(person.getCars());
		return cars;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Posrednik> getAllClients() {
		return sessionFactory.getCurrentSession().getNamedQuery("person.all")
				.list();
	}

	@Override
	public Posrednik findClientByPin(String pin) {
		return (Posrednik) sessionFactory.getCurrentSession().getNamedQuery("person.byPin").setString("pin", pin).uniqueResult();
	}


	@Override
	public Long addNewCar(Nieruchomosc car) {
		car.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(car);
	}

	@Override
	public void sellCar(Long personId, Long carId) {
		Posrednik person = (Posrednik) sessionFactory.getCurrentSession().get(
				Posrednik.class, personId);
		Nieruchomosc car = (Nieruchomosc) sessionFactory.getCurrentSession()
				.get(Nieruchomosc.class, carId);
		car.setSold(true);
		person.getCars().add(car);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Nieruchomosc> getAvailableCars() {
		return sessionFactory.getCurrentSession().getNamedQuery("car.unsold")
				.list();
	}
	@Override
	public void disposeCar(Posrednik person, Nieruchomosc car) {

		person = (Posrednik) sessionFactory.getCurrentSession().get(Posrednik.class,
				person.getId());
		car = (Nieruchomosc) sessionFactory.getCurrentSession().get(Nieruchomosc.class,
				car.getId());

		Nieruchomosc toRemove = null;
		// lazy loading here (person.getCars)
		for (Nieruchomosc aCar : person.getCars())
			if (aCar.getId().compareTo(car.getId()) == 0) {
				toRemove = aCar;
				break;
			}

		if (toRemove != null)
			person.getCars().remove(toRemove);

		car.setSold(false);
	}

	@Override
	public Nieruchomosc findCarById(Long id) {
		return (Nieruchomosc) sessionFactory.getCurrentSession().get(Nieruchomosc.class, id);
	}

}
