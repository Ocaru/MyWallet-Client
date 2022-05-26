package pl.piasecki.MyWalletClient.model;


import java.util.List;


public class User {

	private long id;
	private String name;
	private String surname;
	private String email;
	private List<Expenditure> expenditureList;
	
	public User() {}
	
	public User( String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public List<Expenditure> getExpenditureList() {
		return expenditureList;
	}

	public void setExpenditureList(List<Expenditure> expenditureList) {
		this.expenditureList = expenditureList;
	}
	
	
	




	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", expenditureList="
				+ expenditureList + "]";
	}


	
	
	
}
