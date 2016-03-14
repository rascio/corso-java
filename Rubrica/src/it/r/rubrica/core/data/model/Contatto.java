package it.r.rubrica.core.data.model;

public class Contatto {

	private Integer id;
	private String nome;
	private String cognome;
	private String telefono;
	private String email;
	private Integer utenteId;

	public Contatto() {

	}

	public Contatto(Integer id, String nome, String cognome, String telefono,
			String email, Integer utenteId) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.email = email;
		this.utenteId = utenteId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUtenteId() {
		return utenteId;
	}

	public void setUtenteId(Integer utenteId) {
		this.utenteId = utenteId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result
				+ ((utenteId == null) ? 0 : utenteId.hashCode());
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
		Contatto other = (Contatto) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (utenteId == null) {
			if (other.utenteId != null)
				return false;
		} else if (!utenteId.equals(other.utenteId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contatto [id=" + id + ", nome=" + nome + ", cognome=" + cognome
				+ ", telefono=" + telefono + ", email=" + email + ", utenteId="
				+ utenteId + "]";
	}

}
