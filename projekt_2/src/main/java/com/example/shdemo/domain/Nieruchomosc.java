package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "nieruchomosc.all", query = "Select n from Nieruchomosc n"),
	@NamedQuery(name = "nieruchomosc.byId", query = "Select n from Nieruchomosc n where n.id = :id"),
	@NamedQuery(name = "nieruchomosc.byMiasto", query = "Select n from Nieruchomosc n where n.miasto = :miasto")
})
public class Nieruchomosc 
{
	private Long id;
	private String miasto = "";
	private String kodPocztowy = "";
	private String ulica = "";
	private String nrBloku = "";

	private List<Posrednik> posredniki = new ArrayList<Posrednik>();

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
	public String getMiasto() 
	{
		return miasto;
	}
	public void setMiasto(String miasto) 
	{
		this.miasto = miasto;
	}
	public String getKodPocztowy() 
	{
		return kodPocztowy;
	}
	public void setKodPocztowy(String kodPocztowy) 
	{
		this.kodPocztowy = kodPocztowy;
	}
	public String getUlica() 
	{
		return ulica;
	}
	public void setUlica(String ulica) 
	{
		this.ulica = ulica;
	}
	public String getNrBloku() 
	{
		return nrBloku;
	}
	public void setNrBloku(String nrBloku) 
	{
		this.nrBloku = nrBloku;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Posrednik> getPosredniki() {
		return posredniki;
	}
	public void setPosredniki(List<Posrednik> posredniki) {
		this.posredniki = posredniki;
	}
}
