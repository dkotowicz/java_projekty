package main.java;

import java.sql.Date;

public class Posrednik 
{
	private int id_posrednik;
	private String nazwa;
	private String regon;
	private int id_nieruchomosc;
	
	/*public Posrednik(int id_posrednik, String nazwa, String nip, String regon, Date data_zalozenia, int id_nieruchomosc) {
		super();
		this.id_posrednik=id_posrednik;
		this.nazwa = nazwa;
		this.regon = regon;
		this.id_nieruchomosc = id_nieruchomosc;
	}*/
	public int get_id_posrednik() {
		return id_posrednik;
	}
	public void set_id_posrednik(int id_posrednik) {
		this.id_posrednik = id_posrednik;
	}
	public String get_nazwa() {
		return nazwa;
	}
	public void set_nazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public String get_regon() {
		return regon;
	}
	public void set_regon(String regon) {
		this.regon = regon;
	}
	public int get_id_nieruchomosc() {
		return id_nieruchomosc;
	}
	public void set_id_nieruchomosc(int id_nieruchomosc) {
		this.id_nieruchomosc = id_nieruchomosc;
	}
}
