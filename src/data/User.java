package data;

public class User {
	private String username;
	private String password;
	
	public User(){}
	//admin
	public User(String uN,String pW)
	{
		username=uN;
		password=pW;
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
	
	
	
}
