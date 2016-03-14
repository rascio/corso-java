package it.r.rubrica.core.application.utenze.dto.output;

public class LoginResult {

	private Boolean success;
	private UserInfo user;

	public LoginResult() {
		this.success = false;
	}

	public LoginResult(UserInfo user) {
		this.success = true;
		this.user = user;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((success == null) ? 0 : success.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		LoginResult other = (LoginResult) obj;
		if (success == null) {
			if (other.success != null)
				return false;
		} else if (!success.equals(other.success))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LoginResult [success=" + success + ", user=" + user + "]";
	}

}
