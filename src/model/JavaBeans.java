package model;

public class JavaBeans {
	
	//Variables for temporary Storage
	
	private String conId;
	private String conName;
	private String conPhone;
	private String conEmail;
	
	
	public JavaBeans() {
		super();
	}
	
	
	
	public JavaBeans(String conId, String conName, String conPhone, String conEmail) {
		super();
		this.conId = conId;
		this.conName = conName;
		this.conPhone = conPhone;
		this.conEmail = conEmail;
	}



	public String getConId() {
		return conId;
	}
	public void setConId(String conId) {
		this.conId = conId;
	}
	public String getConName() {
		return conName;
	}
	public void setConName(String conName) {
		this.conName = conName;
	}
	public String getConPhone() {
		return conPhone;
	}
	public void setConPhone(String conPhone) {
		this.conPhone = conPhone;
	}
	public String getConEmail() {
		return conEmail;
	}
	public void setConEmail(String conEmail) {
		this.conEmail = conEmail;
	}
	
	
}
