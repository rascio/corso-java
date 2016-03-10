package it.r.corsi.java.jdbc.esercizio.model;

public class Utente {

	private Integer id;
	private String username;
	private String password;
	private Ruolo ruolo;
	private Boolean abilitato;
	
	public Utente() {
	}
	
	public Utente(Integer id, String username, String password, Ruolo ruolo, Boolean abilitato) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.ruolo = ruolo;
		this.abilitato = abilitato;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public Boolean getAbilitato() {
		return abilitato;
	}

	public void setAbilitato(Boolean abilitato) {
		this.abilitato = abilitato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((abilitato == null) ? 0 : abilitato.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((ruolo == null) ? 0 : ruolo.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		Utente other = (Utente) obj;
		if (abilitato == null) {
			if (other.abilitato != null)
				return false;
		} else if (!abilitato.equals(other.abilitato))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (ruolo != other.ruolo)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", username=" + username + ", password="
				+ password + ", ruolo=" + ruolo + ", abilitato=" + abilitato
				+ "]";
	}
	
	
}
