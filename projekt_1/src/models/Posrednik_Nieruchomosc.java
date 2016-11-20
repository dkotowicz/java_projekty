package models;

import java.sql.Date;

public class Posrednik_Nieruchomosc 
{
	private int id;
	private Date data_dodania;
	private Nieruchomosc nieruchomosc;
	private Posrednik posrednik;
	
	public Posrednik_Nieruchomosc(){}
	
	public int get_id() {
		return id;
	}
	public void set_id(int id) {
		this.id = id;
	}
	public Date get_data_dodania() {
		return data_dodania;
	}
	public void set_data_dodania(Date data_dodania) {
		this.data_dodania = data_dodania;
	}
	public Nieruchomosc get_nieruchomosc() {
		return nieruchomosc;
	}
	public void set_nieruchomosc(Nieruchomosc nieruchomosc) {
		this.nieruchomosc = nieruchomosc;
	}
	public Posrednik get_posrednik() {
		return posrednik;
	}
	public void set_posrednik(Posrednik posrednik) {
		this.posrednik = posrednik;
	}
}
