package data;

import java.io.Serializable;

public class Customer implements Serializable {

	private String goerName;
	private String mobilePhone;
	private String email;
	private int age;
	public Customer(String gN,String mP,String eM)
	{
		goerName=gN;
		mobilePhone=mP;
		email=eM;
	}

	public String getGoerName() {
		return goerName;
	}

	public void setGoerName(String goerName) {
		this.goerName = goerName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
