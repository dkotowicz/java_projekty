package models;

public class Posrednik 
{
	private int id;
	private String nazwa;
	private String nip;
	private String regon;
	private int data_zalozenia;
	
	public Posrednik(int id, String nazwa, String nip, String regon, int data_zalozenia) {
		super();
		this.id = id;
		this.nazwa = nazwa;
		this.nip = nip;
		this.regon = regon;
		this.data_zalozenia = data_zalozenia;
	}
	
	public int get_id() {
		return id;
	}
	public void set_id(int id) {
		this.id = id;
	}
	public String get_nazwa() {
		return nazwa;
	}
	public void set_nazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public String get_nip() {
		return nip;
	}
	public void set_nip(String nip) {
		this.nip = nip;
	}
	public String get_regon() {
		return regon;
	}
	public void set_regon(String regon) {
		this.regon = regon;
	}
	public int get_data_zalozenia() {
		return data_zalozenia;
	}
	public void set_data_zalozenia(int data_zalozenia) {
		this.data_zalozenia = data_zalozenia;
	}
}
