package it.agecontrol.corsi.java.lezione4;

import java.util.Objects;

public class Indirizzo {
	
	private String via;
	private String citta;
	
	public Indirizzo() {}
	
	public Indirizzo(String via, String citta) {
		this.via = via;
		this.citta = citta;
	}
	
	public String getCitta() {
		return citta;
	}
	
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public String getVia() {
		return via;
	}
	
	public void setVia(String via) {
		this.via = via;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citta == null) ? 0 : citta.hashCode());
		result = prime * result + ((via == null) ? 0 : via.hashCode());
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
		Indirizzo other = (Indirizzo) obj;
		
		return Objects.equals(this.citta, other.citta)
			&& Objects.equals(this.via, other.via);
	}

	@Override
	public String toString() {
		return "Indirizzo [via=" + via + ", citta=" + citta + "]";
	}
}
