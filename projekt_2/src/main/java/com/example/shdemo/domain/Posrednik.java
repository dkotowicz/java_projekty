package com.example.shdemo.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "posrednik.all", query = "Select p from Posrednik p"),
	@NamedQuery(name = "posrednik.byId", query = "Select p from Posrednik p where p.id = :id"),
	@NamedQuery(name = "posrednik.byNazwa", query = "Select p from Posrednik p where p.nazwa = :nazwa")
})

public class Posrednik 
{
	private Long id;
	private String nazwa;
	private String regon;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() 
	{
		return id;
	}
	public void setId(Long id) 
	{
		this.id = id;
	}
	public String getNazwa() 
	{
		return nazwa;
	}
	public void setNazwa(String nazwa) 
	{
		this.nazwa = nazwa;
	}
	public String getRegon() 
	{
		return regon;
	}
	public void setRegon(String regon) 
	{
		this.regon = regon;
	}
}
