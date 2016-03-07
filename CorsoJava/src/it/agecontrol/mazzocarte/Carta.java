package it.agecontrol.mazzocarte;

public class Carta {
	
	public static Carta bastoni(Integer v) {
		return new Carta(BASTONI, v);
	}
	
	public static Carta spade(Integer valore) {
		return new Carta(SPADE, valore);
	}
	
	public static Carta denara(Integer valore) {
		return new Carta(DENARA, valore);
	}
	
	public static Carta coppe(Integer valore) {
		return new Carta(COPPE, valore);
	}
	
	public static final String BASTONI = "bastoni";
	public static final String SPADE = "spade";
	public static final String DENARA = "denara";
	public static final String COPPE = "coppe";

	private String seme;
	private Integer valore;
	
	private Carta(String seme, Integer valore) {
		if (valore < 1 || valore > 10) {
			throw new RuntimeException("valore non va bene " + valore);
		}
		
		this.seme = seme;
		this.valore = valore;
		
		
	}

	public String getSeme() {
		return seme;
	}
	
	public Integer getValore() {
		return valore;
	}


	@Override
	public String toString() {
		return "Carta [seme=" + seme + ", valore=" + valore + "]";
	}
	
	
}
