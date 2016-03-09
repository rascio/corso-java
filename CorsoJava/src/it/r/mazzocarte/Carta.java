package it.r.mazzocarte;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seme == null) ? 0 : seme.hashCode());
		result = prime * result + ((valore == null) ? 0 : valore.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carta other = (Carta) obj;
		if (seme == null) {
			if (other.seme != null)
				return false;
		} else if (!seme.equals(other.seme))
			return false;
		if (valore == null) {
			if (other.valore != null)
				return false;
		} else if (!valore.equals(other.valore))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Carta [seme=" + seme + ", valore=" + valore + "]";
	}
	
	
}
