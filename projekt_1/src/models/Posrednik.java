package models;

import java.sql.Date;

public class Posrednik 
{
	private String nazwa;
	private String regon;
	private Date data_zalozenia;
	
	public Posrednik(String nazwa, String nip, String regon, Date data_zalozenia) {
		super();
		this.nazwa = nazwa;
		this.regon = regon;
		this.data_zalozenia = data_zalozenia;
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
	public Date get_data_zalozenia() {
		return data_zalozenia;
	}
	public void set_data_zalozenia(Date data_zalozenia) {
		this.data_zalozenia = data_zalozenia;
	}
}
