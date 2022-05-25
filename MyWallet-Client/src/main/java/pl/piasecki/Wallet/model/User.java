package pl.piasecki.Wallet.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String surname;
	private String email;
	
	public User() {}
	
	public User( String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name ="post_id", updatable = false, insertable = false) 
	private List<Expenditure> expenditureList;
	
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
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + "]";
	}
	
	
	
	
}
