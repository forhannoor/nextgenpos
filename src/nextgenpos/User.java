package nextgenpos;

public class User {

	private int id;
	private String username;
	private String password;
	private String lastLogin;
	
	public User(int id, String username, String password, String lastLogin) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.lastLogin = lastLogin;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public String toString(){
		return id + ", " + username + ", " + password + ", " + lastLogin;
	}
}