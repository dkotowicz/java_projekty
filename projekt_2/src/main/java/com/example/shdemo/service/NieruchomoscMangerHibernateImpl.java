package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Posrednik;
import com.example.shdemo.domain.Nieruchomosc;

@Component
@Transactional
public class NieruchomoscMangerHibernateImpl implements NieruchomosciManager 
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory _sessionFactory)
	{
		this.sessionFactory = _sessionFactory;
	}
	
	@Override
	public void addNieruchomosc(Nieruchomosc nieruchomosc) 
	{
		sessionFactory.getCurrentSession().persist(nieruchomosc);
	}
	
	@Override
	public Long addPosrednik(Posrednik posrednik) 
	{
		posrednik.setId(null);
		return (Long)sessionFactory.getCurrentSession().save(posrednik);
	}

	@Override
	public List<Nieruchomosc> getAllNieruchmosci() 
	{
		return sessionFactory.getCurrentSession().getNamedQuery("nieruchomosc.all").list();
	}
	
	@Override
	public List<Posrednik> getAllPosredniki() 
	{
		return sessionFactory.getCurrentSession().getNamedQuery("posrednik.all").list();
	}
	
	@Override
	public boolean editNieruchomosc(Nieruchomosc nieruchomosc) 
	{
		try{
			sessionFactory.getCurrentSession().update(nieruchomosc);
		}catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	@Override
	public boolean editPosrednik(Posrednik posrednik) 
	{
		try{
			sessionFactory.getCurrentSession().update(posrednik);
		}catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	@Override
	public Nieruchomosc findNieruchomoscbyId(Long id) 
	{
		return (Nieruchomosc) sessionFactory.getCurrentSession().get(Nieruchomosc.class, id);
	}
	
	@Override
	public Posrednik findPosrednikById(Long id) 
	{
		return (Posrednik) sessionFactory.getCurrentSession().get(Posrednik.class, id);
	}
	
	@Override
	public Nieruchomosc findNieruchomoscbyMiasto(String miasto) 
	{
		return (Nieruchomosc) sessionFactory.getCurrentSession().getNamedQuery("nieruchomosc.byMiasto").setString("miasto", miasto).list().get(0);
	}
	
	@Override
	public Posrednik findPosrednikbyNazwa(String nazwa) 
	{
		return (Posrednik) sessionFactory.getCurrentSession().getNamedQuery("posrednik.byNazwa").setString("nazwa", nazwa).list().get(0);
	}
	
	@Override
	public void deleteNieruchomosc(Nieruchomosc nieruchomosc) 
	{
		nieruchomosc = (Nieruchomosc) sessionFactory.getCurrentSession().get(Nieruchomosc.class, nieruchomosc.getId());
		for(Posrednik posrednik : nieruchomosc.getPosredniki())
		{
			sessionFactory.getCurrentSession().delete(posrednik);
		}
		sessionFactory.getCurrentSession().delete(nieruchomosc);
	}
	
	@Override
	public void deletePosrednik(Posrednik posrednik) 
	{
		Posrednik _posrednik = (Posrednik) sessionFactory.getCurrentSession().get(Posrednik.class, posrednik.getId());
		
		List<Nieruchomosc> nieruchomosci = getAllNieruchmosci();
		for(Nieruchomosc n : nieruchomosci){
		for(Posrednik p : n.getPosredniki()){
			if(n.getId() == _posrednik.getId()){
				n.getPosredniki().remove(p);
				sessionFactory.getCurrentSession().update(n);
				break;
				}
			}
		}
		sessionFactory.getCurrentSession().delete(_posrednik);
	}
	
}
