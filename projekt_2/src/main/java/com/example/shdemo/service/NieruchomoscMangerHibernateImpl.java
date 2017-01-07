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
	
	
	
}
