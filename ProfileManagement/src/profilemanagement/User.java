package profilemanagement;

public class User {
	private String username;
	private String password;
	private String email;
	private String contact;

	
	public User(String username, String password, String email, String contact) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.contact = contact;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContactDetails(String contactDetails) {
		this.contact = contactDetails;
	}

	@Override
	public String toString() {
		return "User{" + "username='" + username + '\'' + ", email='" + email + '\'' + ", contact='"
				+ contact + '\'' + '}';
	}
}
