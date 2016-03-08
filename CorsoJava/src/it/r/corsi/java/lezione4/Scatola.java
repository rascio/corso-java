package it.r.corsi.java.lezione4;

public class Scatola {
//	
//	public static Scatola vuota() {
//		return new Scatola(null);
//	}

	private String contenuto;
	private Integer lato;
	
	public Scatola(String contenuto, Integer l) {
		System.out.println("Sono nel costruttore di scatola");
		if (l <= 0) {
			throw new RuntimeException("il lato non può essere negativo: " + l);
		}
		this.lato = l;
		this.contenuto = contenuto;
	}
	
	public Integer volume() {
		return this.lato * this.lato * this.lato;
	}
	
	public static Integer volume2(Scatola s) {
		return s.lato * s.lato * s.lato;
	}
	
//	public Scatola(String contenuto) {
//		this.contenuto = contenuto;
//	}
	
	public String getContenuto() {
		return this.contenuto;
	}
	
	public boolean isVuota() {
		return this.contenuto == null;
	}
	
	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}
}
