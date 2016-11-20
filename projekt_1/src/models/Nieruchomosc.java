package models;

public class Nieruchomosc 
{
	private String nr;
	private String miasto;
	private String kod_pocztowy;
	private float czynsz;
	private String ulica;
	private String nr_bloku;
	
	public String get_nr() {
		return nr;
	}
	public void set_nr(String nr) {
		this.nr = nr;
	}
	public String get_miasto() {
		return miasto;
	}
	public void set_miasto(String miasto) {
		this.miasto = miasto;
	}
	public String get_kod_pocztowy() {
		return kod_pocztowy;
	}
	public void set_kod_pocztowy(String kod_pocztowy) {
		this.kod_pocztowy = kod_pocztowy;
	}

	public float get_czynsz() {
		return czynsz;
	}
	public void set_czynsz(float czynsz) {
		this.czynsz = czynsz;
	}
	public String get_ulica() {
		return ulica;
	}
	public void set_ulica(String ulica) {
		this.ulica = ulica;
	}
	public String get_nr_bloku() {
		return nr_bloku;
	}
	public void set_nr_bloku(String nr_bloku) {
		this.nr_bloku = nr_bloku;
	}
}
