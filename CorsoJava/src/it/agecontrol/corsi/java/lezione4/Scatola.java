package it.agecontrol.corsi.java.lezione4;

public class Scatola {
	
	public static Scatola vuota() {
		return new Scatola(null);
	}

	private String contenuto;
	
	public Scatola(String contenuto) {
		this.contenuto = contenuto;
	}
	
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
